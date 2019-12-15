package com.example.oslotest;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.subscribers.DefaultSubscriber;
import jp.wasabeef.blurry.Blurry;

public class HomeFragment extends Fragment implements HomeAdapter.ViewHolder.OpenProfileListener{
    private SwipeRefreshLayout mSwipeLyt;
    private RecyclerView mRecyclerView;
    private RecyclerView hotRecyclerView;
    private RecyclerView horizonRecylerView;
    private HomeAdapter mAdapter;
    private StackExchangeManager mManager;
    int blur_temp = 255;

    public static HomeFragment instance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getActivity()));

        mSwipeLyt = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.home_rv);
        hotRecyclerView = (RecyclerView) v.findViewById(R.id.home_hot_rv);
        horizonRecylerView = (RecyclerView) v.findViewById(R.id.home_horizon_rv);

        mAdapter = new HomeAdapter(getActivity(), new ArrayList<User>());
        mAdapter.setOpenProfileListener(this);
        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);
        //llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        hotRecyclerView.setLayoutManager(glm);
        hotRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager llm_hor = new LinearLayoutManager(getActivity());
        llm_hor.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizonRecylerView.setLayoutManager(llm_hor);
        horizonRecylerView.setAdapter(mAdapter);

        horizonRecylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("테스트", "dx" + dx + " dy" + dy);
                if (blur_temp > 0) {
                    blur_temp -= Math.abs(dx);
                }
            }
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == horizonRecylerView.SCROLL_STATE_DRAGGING) {
                    // Do something
                    Log.d("테스트", "dragging");
                } else if (newState == horizonRecylerView.SCROLL_STATE_IDLE) {
                    Log.d("테스트", "idle");
                } else {
                    // Do something
                }
            }
        });

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(horizonRecylerView);
        mManager = new StackExchangeManager();


        mSwipeLyt.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });
        refreshList();

        return v;
    }

    private void refreshList(){
        showRefresh(true);
        mManager.getMostPopularSQysers(10)
                .subscribe(new DefaultSubscriber<List<User>>() {
                    @Override
                    public void onNext(List<User> users) {
                        showRefresh(false);
                        mAdapter.updateUsers(users);
                    }

                    @Override
                    public void onError(Throwable t) {
                        showRefresh(false);
                    }

                    @Override
                    public void onComplete() {}
                } );
    }


    private void showRefresh(boolean show){
        mSwipeLyt.setRefreshing(show);
        int visibility = show ? View.GONE : View.VISIBLE;
        mRecyclerView.setVisibility(visibility);
    }
    @Override
    public void open(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
