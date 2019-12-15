package com.example.oslotest.viewmodelimpl.screen;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.oslotest.FragmentPagerAdapter;
import com.example.oslotest.R;
import com.example.oslotest.view.LoginView;
import com.example.oslotest.view.MainView;
import com.example.oslotest.viewmodel.screen.LoginScreenViewModel;
import com.example.oslotest.viewmodel.screen.MainScreenViewModel;
import com.google.android.material.tabs.TabLayout;

import java.lang.ref.WeakReference;

import androidx.databinding.Observable;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

public class MainScreenViewModelImpl extends ViewModel implements MainScreenViewModel {

    public final ObservableInt currentPage = new ObservableInt();

    private WeakReference<Activity> mActivityRef;


    public MainScreenViewModelImpl() {
    }

    @Override
    public void setMainView(MainView view) {
        //view.setActionListener(this);
        TabLayout tabLayout = mActivityRef.get().findViewById(R.id.tab_layout);
        tabLayout.getTabAt(0).setCustomView(createTabView("메인"));
        tabLayout.getTabAt(1).setCustomView(createTabView("마이페이지"));
        //tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Log.d("테스트 addontab", tab.getPosition()+"");
//                currentPage.set(tab.getPosition());
//                Log.d("테스트 addontab", currentPage.get()+"");
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public void setParentContext(Activity parentContext) {
        mActivityRef = new WeakReference<>(parentContext);
    }
    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(mActivityRef.get()).inflate(R.layout.custom_tab, null);
        TextView tabName_tv = (TextView) tabView.findViewById(R.id.tab_name);
        tabName_tv.setText(tabName);
        return tabView;

    }
}
