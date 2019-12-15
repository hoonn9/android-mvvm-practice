package com.example.oslotest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oslotest.databinding.FragmentMypageBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MyPageFragment extends Fragment {

    public static MyPageFragment instance() {
        return new MyPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMypageBinding layout = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false);
        layout.setView(this);
        return layout.getRoot();
    }

    public void onLoginButtonClick(View view) {
        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
        startActivity(loginIntent);
    }
}
