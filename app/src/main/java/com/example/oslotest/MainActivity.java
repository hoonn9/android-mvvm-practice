package com.example.oslotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.couchbase.lite.BasicAuthenticator;
import com.couchbase.lite.CouchbaseLite;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Document;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.Expression;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.couchbase.lite.URLEndpoint;
import com.example.oslotest.databinding.ActivityMainBinding;
import com.example.oslotest.injection.ViewInjection;
import com.example.oslotest.injection.ViewModelInjection;
import com.example.oslotest.view.LoginView;
import com.example.oslotest.view.MainView;
import com.example.oslotest.viewmodel.screen.LoginScreenViewModel;
import com.example.oslotest.viewmodel.screen.MainScreenViewModel;
import com.example.oslotest.viewmodelimpl.screen.LoginScreenViewModelImpl;
import com.example.oslotest.viewmodelimpl.screen.MainScreenViewModelImpl;
import com.google.android.material.tabs.TabLayout;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "Couchbase :";

    //private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    //private TabLayout tabLayout;
    private ImageView main_toolbar_iv;

    private MainScreenViewModel mScreenViewModel;
    ActivityMainBinding binding;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setView(this);

        mScreenViewModel = ViewModelProviders.of(this).get(MainScreenViewModelImpl.class);
        mScreenViewModel.setParentContext(this);
        injectView(mScreenViewModel);

        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        main_toolbar_iv = (ImageView) findViewById(R.id.main_toolbar_iv);
        Glide.with(this).load("http://m.oslokorea.co.kr/data/goods/18/03/11/1000000026/1000000026_detail_046.jpg").centerCrop().into(main_toolbar_iv);

        //tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("메인")));
        //tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("마이페이지")));
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.add(HomeFragment.instance());
        fragmentPagerAdapter.add(MyPageFragment.instance());
        binding.viewpager.setAdapter(fragmentPagerAdapter);

        //viewPager.setAdapter(fragmentPagerAdapter);
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        // Initialize the Couchbase Lite system
        CouchbaseLite.init(this);

// Get the database (and create it if it doesn’t exist).
        DatabaseConfiguration config = new DatabaseConfiguration();


        try {
            database = new Database("mydb", config);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }


// Create a new document (i.e. a record) in the database.
        MutableDocument mutableDoc = new MutableDocument()
                .setFloat("version", 2.0F)
                .setString("type", "SDK");

// Save it to the database.
        try {
            database.save(mutableDoc);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }


// Update a document.
        mutableDoc = database.getDocument(mutableDoc.getId()).toMutable();
        mutableDoc.setString("language", "Java");

        try {
            database.save(mutableDoc);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        Document document = database.getDocument(mutableDoc.getId());
// Log the document ID (generated by the database) and properties
        Log.i(TAG, "Document ID :: " + document.getId());
        Log.i(TAG, "Learning " + document.getString("language"));

// Create a query to fetch documents of type SDK.
        Query query = QueryBuilder.select(SelectResult.all())
                .from(DataSource.database(database))
                .where(Expression.property("type").equalTo(Expression.string("SDK")));

        ResultSet result = null;
        try {
            result = query.execute();
            for (Result result1 : result.allResults()) {
                Log.i(
                        "Sample",
                        String.format("public_likes -> %s", result1.getString("language")));
            }
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }


        //Log.i(TAG, "Number of rows ::  " + result.allResults().size());

// Create replicators to push and pull changes to and from the cloud.
        Endpoint targetEndpoint = null;
        try {
            targetEndpoint = new URLEndpoint(new URI("ws://10.0.2.2:4985/getting-started-db"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ReplicatorConfiguration replConfig = new ReplicatorConfiguration(database, targetEndpoint);
        replConfig.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);

// Add authentication.
        replConfig.setAuthenticator(new BasicAuthenticator("sync_gateway", "123456"));

// Create replicator (be sure to hold a reference somewhere that will prevent the Replicator from being GCed)
        Replicator replicator = new Replicator(replConfig);

// Listen to replicator change events.
        replicator.addChangeListener(change -> {
            if (change.getStatus().getError() != null) {
                Log.i(TAG, "Error code ::  " + change.getStatus().getError().getCode());
            }
        });

// Start replication.
        replicator.start();
    }

    private void injectViewModel(MainScreenViewModel viewModel) {
        //viewModel.setLoginUsecaseExecutor(ViewModelInjection.provideLoginUsecaseExecutor(this));
    }

    private void injectView(MainScreenViewModel viewModel) {
        //viewModel.setToastView(ViewInjection.provideToastView());

        MainView mainView = ViewInjection.provideMainView(findViewById(R.id.drawer_layout), this);
        viewModel.setMainView(mainView);
    }

    //    private View createTabView(String tabName) {
//        View tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        TextView tabName_tv = (TextView) tabView.findViewById(R.id.tab_name);
//        tabName_tv.setText(tabName);
//        return tabView;
//
//    }

}
