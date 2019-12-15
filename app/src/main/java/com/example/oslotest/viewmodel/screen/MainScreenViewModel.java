package com.example.oslotest.viewmodel.screen;

import android.app.Activity;

import com.example.oslotest.view.MainView;

public interface MainScreenViewModel {
    void setParentContext(Activity parentContext);
    void setMainView(MainView view);
}
