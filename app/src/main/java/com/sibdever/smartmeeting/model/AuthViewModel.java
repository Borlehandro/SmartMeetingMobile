package com.sibdever.smartmeeting.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sibdever.smartmeeting.data.response.AuthResponse;

public class AuthViewModel extends ViewModel {

    private final MutableLiveData<AuthResponse> authResponse = new MutableLiveData<>();

    public MutableLiveData<AuthResponse> getAuthResponse() {
        return authResponse;
    }
}