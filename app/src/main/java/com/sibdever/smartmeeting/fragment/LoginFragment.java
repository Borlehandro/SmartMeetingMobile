package com.sibdever.smartmeeting.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sibdever.smartmeeting.R;
import com.sibdever.smartmeeting.activity.LoginActivity;
import com.sibdever.smartmeeting.data.request.LoginRequest;
import com.sibdever.smartmeeting.model.LoginRegisterViewModel;

public class LoginFragment extends Fragment {
    private LoginRegisterViewModel loginRegisterViewModel;

    private Button loginButton;
    private EditText emailText, passwordText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = v.findViewById(R.id.login_button);
        emailText = v.findViewById(R.id.login_email);
        passwordText = v.findViewById(R.id.login_password);

        loginButton.setOnClickListener((view) -> {
            if(!emailText.getText().toString().isEmpty()
                    && !passwordText.getText().toString().isEmpty()) {

                Log.d("Sibdever", "onCreateView: SEND LOGIN");

                loginRegisterViewModel.getLoginRequest().setValue(new LoginRequest(
                        emailText.getText().toString(),
                        passwordText.getText().toString()
                ));
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginRegisterViewModel = ((LoginActivity)getActivity()).getLoginRegisterViewModel();
    }
}
