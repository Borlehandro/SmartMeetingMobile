package com.sibdever.smartmeeting.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sibdever.smartmeeting.data.request.AuthRequest;
import com.sibdever.smartmeeting.data.request.LoginRequest;
import com.sibdever.smartmeeting.data.request.RegisterRequest;
import com.sibdever.smartmeeting.data.response.AuthResponse;
import com.sibdever.smartmeeting.data.response.LoginResponse;
import com.sibdever.smartmeeting.data.response.RegisterResponse;

public class LoginRegisterViewModel extends ViewModel {

    private final MutableLiveData<LoginRequest> loginRequest = new MutableLiveData<>();
    private final MutableLiveData<RegisterRequest> registerRequest = new MutableLiveData<>();

    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    private final MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();

    public MutableLiveData<LoginRequest> getLoginRequest() {
        return loginRequest;
    }

    public MutableLiveData<RegisterRequest> getRegisterRequest() {
        return registerRequest;
    }

    public MutableLiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public MutableLiveData<RegisterResponse> getRegisterResponse() {return registerResponse;}
}
