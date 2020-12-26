package com.sibdever.smartmeeting.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sibdever.smartmeeting.R;

public class PreLoginFragment extends Fragment implements View.OnClickListener {

    private NavController controller;
    private Button registerButton, loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pre_login, container, false);
        registerButton = v.findViewById(R.id.registerChoice);
        loginButton = v.findViewById(R.id.loginChoice);
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.registerChoice) {
            controller.navigate(R.id.toRegistration);
        } else if(view.getId() == R.id.loginChoice) {
            controller.navigate(R.id.toLogin);
        }
    }
}
