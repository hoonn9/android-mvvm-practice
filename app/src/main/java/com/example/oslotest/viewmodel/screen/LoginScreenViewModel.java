package com.example.oslotest.viewmodel.screen;

import android.app.Activity;
import android.content.Intent;

import com.example.oslotest.view.LoginView;
import com.example.oslotest.view.ToastView;
import com.example.oslotest.viewmodel.usecase.LoginUsecaseExecutor;
/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public interface LoginScreenViewModel extends LoginView.ActionListener {

    void setParentContext(Activity parentContext);

    void setLoginUsecaseExecutor(LoginUsecaseExecutor executor);

    void setToastView(ToastView view);

    void setLoginView(LoginView view);

    void loadUserData();

    boolean onActivityResult(int requestCode, int resultCode, Intent data);
}
