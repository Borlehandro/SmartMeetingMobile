package com.sibdever.smartmeeting.service;

import androidx.lifecycle.LifecycleOwner;

import com.sibdever.smartmeeting.RestController;
import com.sibdever.smartmeeting.model.AuthViewModel;

public class AuthService {
    private final AuthViewModel authViewModel;
    private final LifecycleOwner lifecycleOwner;

    public AuthService(AuthViewModel authViewModel, LifecycleOwner lifecycleOwner) {
        this.authViewModel = authViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void tryToAuth(String token) {
        RestController.getInstance().tryAuth(authViewModel.getAuthResponse(), token);
    }

}