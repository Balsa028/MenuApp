package com.balsa.menuapp.Login;

import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.balsa.menuapp.R;
import com.balsa.menuapp.Utils.Constants;
import com.balsa.menuapp.Utils.Util;
import com.balsa.menuapp.Venues.VenuesListFragment;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button btnSignIn;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewToReturn = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(viewToReturn);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        observeLoginState();

        btnSignIn.setOnClickListener(view -> performSignIn(emailEditText, passwordEditText, LoginFragment.this));
        return viewToReturn;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        loginViewModel.configurationChanged(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    private void performSignIn(EditText emailEditText, EditText passwordEditText, LoginFragment fragment) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (validateCredentials(email, password))
            loginViewModel.performSignIn(email, password, fragment);

    }

    private void observeLoginState() {
        //posmatranje promena nad podacima za login
        loginViewModel.getIsLoginSuccessfull().observe(requireActivity(), isSuccessfull -> {
            try {
                switch (isSuccessfull) {
                    case Constants.LOGIN_SUCCESS_KEY:
                        if (!Util.readTokenFromSharedPrefs(requireActivity()).equals("")) {
                            Toast.makeText(getActivity(), requireActivity().getResources().getString(R.string.successfully_logged_in), Toast.LENGTH_SHORT).show();
                            Util.replaceFragment(requireActivity().getSupportFragmentManager(), R.id.fragment_container, VenuesListFragment.newInstance(), "VenuesListFragment");
                        }
                        break;
                    case Constants.LOGIN_WRONG_CREDENTIALS_KEY:
                        Toast.makeText(getActivity(), requireActivity().getResources().getString(R.string.wrong_credentials), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity(), requireActivity().getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        break;
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

        });

        loginViewModel.getEmailEditText().observe(requireActivity(), text -> {
            emailEditText.setText(text);
            emailEditText.setSelection(text.length());
        });

        loginViewModel.getPasswordEditText().observe(requireActivity(), text -> {
            passwordEditText.setText(text);
            passwordEditText.setSelection(text.length());
        });
    }

    private Boolean validateCredentials(String email, String password) {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), requireActivity().getResources().getString(R.string.empty_email_field), Toast.LENGTH_SHORT).show();
            emailEditText.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getActivity(), requireActivity().getResources().getString(R.string.invalid_email_format), Toast.LENGTH_SHORT).show();
            emailEditText.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), requireActivity().getResources().getString(R.string.empty_password_field), Toast.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return false;
        }

        return true;
    }

    private void initViews(View view) {
        emailEditText = view.findViewById(R.id.editTextEmail);
        passwordEditText = view.findViewById(R.id.editTextPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);
    }
}