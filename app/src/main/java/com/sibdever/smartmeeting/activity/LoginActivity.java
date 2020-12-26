package com.sibdever.smartmeeting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.sibdever.smartmeeting.R;
import com.sibdever.smartmeeting.data.ResponseStatus;
import com.sibdever.smartmeeting.model.LoginRegisterViewModel;
import com.sibdever.smartmeeting.service.LoginRegisterService;

public class LoginActivity extends AppCompatActivity {

    private final LoginRegisterViewModel loginRegisterViewModel = new LoginRegisterViewModel();
    private SharedPreferences preferences;
    private LoginRegisterService loginRegisterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        loginRegisterViewModel.getLoginResponse().observe(this, (value) -> {
            Log.d("Sibdever", "Get result from model: " + value.getResponseStatus());
            if (value.getResponseStatus().equals(ResponseStatus.OK)) {
                preferences.edit().putString("token", value.getCookie().getValue()).apply();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("loggedIn", true);
                startActivity(intent);
            }
        });

        // Todo fix duplicate code
        loginRegisterViewModel.getRegisterResponse().observe(this, (value) -> {
            Log.d("Sibdever", "Get result from model: " + value.getResponseStatus());
            if (value.getResponseStatus().equals(ResponseStatus.OK)) {
                preferences.edit().putString("token", value.getCookie().getValue()).apply();
                Log.d("Sibdever", "put in preferences:" + value.getCookie().getValue());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("loggedIn", true);
                startActivity(intent);
            }
        });

        loginRegisterService = new LoginRegisterService(loginRegisterViewModel, this);
        loginRegisterService.observeOnModel();

        preferences = getSharedPreferences("AuthPreferences", MODE_PRIVATE);
    }

    public LoginRegisterViewModel getLoginRegisterViewModel() {
        return loginRegisterViewModel;
    }
}