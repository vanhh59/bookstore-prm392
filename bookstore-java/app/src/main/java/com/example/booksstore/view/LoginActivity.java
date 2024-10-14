package com.example.booksstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loginform.MainActivity;
import com.loginform.contract.LoginContract;
import com.loginform.databinding.LogInMainBinding;
import com.loginform.model.LoginModel;
import com.loginform.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LogInMainBinding binding;
    private LoginContract.Presenter presenter;
    private final String USERNAME_FIELD = "username";
    private final String PASSWORD_FIELD = "password";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = LogInMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new LoginPresenter(new LoginModel(), this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.loginMain, ((v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }));

        binding.loginButton.setOnClickListener(v -> onClickLoginBtn());
        binding.forgotPassword.setOnClickListener(v -> onClickForgotPasswordBtn());
        binding.registerButton.setOnClickListener(v -> onClickRegisterBtn());
    }

    private void onClickRegisterBtn() {
        presenter.handleOnClickRegisterBtn();
    }

    private void onClickForgotPasswordBtn() {
        presenter.handleOnClickForgotPassword();
    }

    private void onClickLoginBtn() {
        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();

        presenter.handleOnClickLoginBtn(username, password);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorField(String field, String type) {
        switch (field) {
            case USERNAME_FIELD:
                binding.username.setError(type);
                break;
            case PASSWORD_FIELD:
                binding.password.setError(type);
                break;
        }
    }

    @Override
    public void redirectRegisterActivity() {
        //Start MainActivity
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void redirectMainActivity() {
        //Start MainActivity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void redirectForgotPasswordActivity() {
        //Start MainActivity
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
        finish();
    }
}
