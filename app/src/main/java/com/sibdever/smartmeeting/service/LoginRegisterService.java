package com.sibdever.smartmeeting.service;

import androidx.lifecycle.LifecycleOwner;

import com.sibdever.smartmeeting.model.LoginRegisterViewModel;
import com.sibdever.smartmeeting.RestController;

public class LoginRegisterService {
    private final LoginRegisterViewModel loginRegisterViewModel;
    private final LifecycleOwner lifecycleOwner;

    public LoginRegisterService(LoginRegisterViewModel loginRegisterViewModel, LifecycleOwner lifecycleOwner) {
        this.loginRegisterViewModel = loginRegisterViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void observeOnModel() {
        loginRegisterViewModel.getLoginRequest().observe(lifecycleOwner, (value) -> {
            if(value != null) {
                RestController.getInstance().login(value, loginRegisterViewModel.getLoginResponse());
            }
        });
        loginRegisterViewModel.getRegisterRequest().observe(lifecycleOwner, (value) -> {
            if(value != null) {
                RestController.getInstance().register(value, loginRegisterViewModel.getRegisterResponse());
            }
        });
    }
}
