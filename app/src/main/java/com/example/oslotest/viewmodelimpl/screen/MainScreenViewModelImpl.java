package com.example.oslotest.viewmodelimpl.screen;

import android.app.Activity;

import com.example.oslotest.viewmodel.screen.LoginScreenViewModel;
import com.example.oslotest.viewmodel.screen.MainScreenViewModel;

import java.lang.ref.WeakReference;

import androidx.lifecycle.ViewModel;

public class MainScreenViewModelImpl extends ViewModel implements MainScreenViewModel {

    private WeakReference<Activity> mActivityRef;

    public MainScreenViewModelImpl() {
    }

    @Override
    public void setParentContext(Activity parentContext) {
        mActivityRef = new WeakReference<>(parentContext);
    }
}
