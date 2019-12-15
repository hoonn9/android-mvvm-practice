package com.example.oslotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import com.example.oslotest.databinding.ActivityLoginBinding;
import com.example.oslotest.injection.ViewInjection;
import com.example.oslotest.injection.ViewModelInjection;
import com.example.oslotest.view.LoginView;
import com.example.oslotest.viewmodel.screen.LoginScreenViewModel;
import com.example.oslotest.viewmodelimpl.screen.LoginScreenViewModelImpl;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private LoginScreenViewModel mScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setView(this);

        mScreenViewModel = ViewModelProviders.of(this).get(LoginScreenViewModelImpl.class);
        mScreenViewModel.setParentContext(this);

        injectViewModel(mScreenViewModel);
        injectView(mScreenViewModel);
    }

    private void injectViewModel(LoginScreenViewModel viewModel) {
        viewModel.setLoginUsecaseExecutor(ViewModelInjection.provideLoginUsecaseExecutor(this));
    }

    private void injectView(LoginScreenViewModel viewModel) {
        viewModel.setToastView(ViewInjection.provideToastView());

        LoginView loginView = ViewInjection.provideLoginView(findViewById(R.id.login_trigger_container), this);
        viewModel.setLoginView(loginView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mScreenViewModel.loadUserData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mScreenViewModel.onActivityResult(requestCode, resultCode, data);
    }
}
