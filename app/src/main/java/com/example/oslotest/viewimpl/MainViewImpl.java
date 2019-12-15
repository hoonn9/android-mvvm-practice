package com.example.oslotest.viewimpl;

import android.view.View;

import com.example.oslotest.R;
import com.example.oslotest.view.LoginView;
import com.example.oslotest.view.MainView;
import com.google.android.gms.common.SignInButton;

import androidx.lifecycle.LifecycleOwner;

public class MainViewImpl implements MainView {

    private View mMainView;
    private LifecycleOwner mLifecycleOwner;
    private LoginView.ActionListener mActionListener;

    public MainViewImpl(View view, LifecycleOwner lifecycleOwner) {
        mMainView = view;
        mLifecycleOwner = lifecycleOwner;

        SignInButton signInButton = mMainView.findViewById(R.id.sign_in_btn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(v -> mActionListener.onRequestedSignIn());
    }


}
