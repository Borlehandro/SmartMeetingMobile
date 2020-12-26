package com.sibdever.smartmeeting.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sibdever.smartmeeting.R;
import com.sibdever.smartmeeting.activity.LoginActivity;
import com.sibdever.smartmeeting.data.request.RegisterRequest;
import com.sibdever.smartmeeting.model.LoginRegisterViewModel;

public class RegisterFragment extends Fragment {

    private EditText registerUsername,
            registerPassword,
            registerEmail,
            registerConfirmPassword;

    private Button registerButton;

    private ProgressBar progressBar;

    private LoginRegisterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        registerButton = v.findViewById(R.id.register_button);
        registerEmail = v.findViewById(R.id.register_email);
        registerPassword = v.findViewById(R.id.register_password);
        registerConfirmPassword = v.findViewById(R.id.register_confirm);
        registerUsername = v.findViewById(R.id.register_user_name);
        progressBar = v.findViewById(R.id.registrationProgress);

        registerButton.setOnClickListener((view) -> {
            if (registerConfirmPassword.getText().toString().equals(
                    registerPassword.getText().toString())) {
                viewModel.getRegisterRequest().setValue(new RegisterRequest(
                        registerUsername.getText().toString(),
                        registerPassword.getText().toString(),
                        registerEmail.getText().toString()
                ));
            } else {
                Toast.makeText(getActivity(), "Passwords don't match!", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ((LoginActivity) getActivity()).getLoginRegisterViewModel();
    }
}
