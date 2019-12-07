package com.example.oslotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private TabLayout tabLayout;
    private ImageView main_toolbar_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        main_toolbar_iv = (ImageView) findViewById(R.id.main_toolbar_iv);
        Glide.with(this).load("http://m.oslokorea.co.kr/data/goods/18/03/11/1000000026/1000000026_detail_046.jpg").centerCrop().into(main_toolbar_iv);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("메인")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("마이페이지")));
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabName_tv = (TextView) tabView.findViewById(R.id.tab_name);
        tabName_tv.setText(tabName);
        return tabView;

    }
}
