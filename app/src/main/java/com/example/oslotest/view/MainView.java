package com.example.oslotest.view;

import com.example.oslotest.view.action.OnNotifySignInSuccessAction;
import com.example.oslotest.view.action.OnRenderToastAction;
import com.example.oslotest.view.action.OnRequestedSignInAction;
import com.google.firebase.auth.FirebaseUser;

import androidx.lifecycle.LiveData;

public interface MainView {

    void setActionListener(MainView.ActionListener actionListener);

    interface ActionListener extends OnNotifySignInSuccessAction, OnRequestedSignInAction,
            OnRenderToastAction {
    }
}
