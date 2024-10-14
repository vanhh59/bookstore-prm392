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

import com.loginform.contract.RegisterContract;
import com.loginform.databinding.RegisterMainBinding;
import com.loginform.model.RegisterModel;
import com.loginform.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private RegisterMainBinding binding;
    private RegisterContract.Presenter presenter;
    private final String USERNAME_FIELD = "username";
    private final String PASSWORD_FIELD = "password";
    private final String CONFIRM_PASSWORD_FIELD = "confirm_password";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = RegisterMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new RegisterPresenter(this, new RegisterModel());

        ViewCompat.setOnApplyWindowInsetsListener(binding.registerMain, ((v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }));

        binding.registerButton.setOnClickListener(v -> onClickRegisterBtn());
        binding.loginRedirect.setOnClickListener(v -> onClickLoginRedirectBtn());
    }

    private void onClickLoginRedirectBtn() {
        presenter.handleOnClickLoginRedirectBtn();
    }

    private void onClickRegisterBtn() {
        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();
        String confirmPassword = binding.confirmPassword.getText().toString();

        presenter.handleOnClickRegisterBtn(username, password, confirmPassword);

    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
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
            case CONFIRM_PASSWORD_FIELD:
                binding.confirmPassword.setError(type);
        }
    }

    @Override
    public void redirectLoginActivity() {
        //Start MainActivity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
