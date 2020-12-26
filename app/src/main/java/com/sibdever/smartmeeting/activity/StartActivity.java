package com.sibdever.smartmeeting.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.sibdever.smartmeeting.R;
import com.sibdever.smartmeeting.data.ResponseStatus;
import com.sibdever.smartmeeting.model.AuthViewModel;
import com.sibdever.smartmeeting.service.AuthService;

public class StartActivity extends AppCompatActivity {

    private final AuthViewModel viewModel = new AuthViewModel();
    private AuthService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        service = new AuthService(viewModel, this);
        viewModel.getAuthResponse().observe(this, (value) -> {
            if (value.getResponseStatus().equals(ResponseStatus.OK)) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("loggedIn", true);
                startActivity(intent);
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("AuthPreferences", MODE_PRIVATE);
        if (preferences.contains("token")) {
            service.tryToAuth(preferences.getString("token", null));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}