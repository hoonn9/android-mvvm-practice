package com.example.oslotest.view;

import androidx.lifecycle.LiveData;

import com.example.oslotest.view.action.OnNotifySignInSuccessAction;
import com.example.oslotest.view.action.OnRenderToastAction;
import com.example.oslotest.view.action.OnRequestedSignInAction;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public interface LoginView {

    void setFirebaseUserLiveData(LiveData<FirebaseUser> liveData);

    void setThrowableUserLiveData(LiveData<Throwable> liveData);

    void setActionListener(ActionListener actionListener);

    interface ActionListener extends OnNotifySignInSuccessAction, OnRequestedSignInAction,
            OnRenderToastAction {
    }
}
