package com.example.oslotest.viewimpl;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.oslotest.R;
import com.example.oslotest.view.LoginView;
import com.example.oslotest.view.MainView;
import com.google.android.gms.common.SignInButton;

import androidx.lifecycle.LifecycleOwner;

public class MainViewImpl implements MainView {

    private View mMainView;
    private LifecycleOwner mLifecycleOwner;
    private MainView.ActionListener mActionListener;

    public MainViewImpl(View view, LifecycleOwner lifecycleOwner) {
        mMainView = view;
        mLifecycleOwner = lifecycleOwner;
    }

    @Override
    public void setActionListener(MainView.ActionListener actionListener) {
        mActionListener = actionListener;
    }

}
