package com.example.oslotest.injection;

import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import com.example.oslotest.view.LoginView;
import com.example.oslotest.view.MainView;
import com.example.oslotest.view.ToastView;
import com.example.oslotest.viewimpl.LoginViewImpl;
import com.example.oslotest.viewimpl.MainViewImpl;
import com.example.oslotest.viewimpl.ToastViewImpl;

/**
 * @author firefly2.kim
 * @since 19. 8. 25
 */
public class ViewInjection {

    private ViewInjection() {
        // No instance
    }

    public static LoginView provideLoginView(View view, LifecycleOwner lifecycleOwner) {
        return new LoginViewImpl(view, lifecycleOwner);
    }

    public static MainView provideMainView(View view, LifecycleOwner lifecycleOwner) {
        return new MainViewImpl(view, lifecycleOwner);
    }

    public static ToastView provideToastView() {
        return new ToastViewImpl();
    }
}
