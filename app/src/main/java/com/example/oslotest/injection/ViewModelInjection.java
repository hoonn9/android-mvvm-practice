package com.example.oslotest.injection;

import android.app.Activity;

import com.example.oslotest.viewmodel.usecase.LoginUsecaseExecutor;
import com.example.oslotest.viewmodelimpl.usecase.LoginUsecaseExecutorImpl;

/**
 * @author firefly2.kim
 * @since 19. 8. 25
 */
public class ViewModelInjection {

    private ViewModelInjection() {
        // No instance
    }

    public static LoginUsecaseExecutor provideLoginUsecaseExecutor(Activity activity) {
        return new LoginUsecaseExecutorImpl(activity);
    }
}
