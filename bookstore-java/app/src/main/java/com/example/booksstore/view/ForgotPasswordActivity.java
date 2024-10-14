package com.example.booksstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loginform.contract.ForgotPasswordContract;
import com.loginform.databinding.ForgotPasswordMainBinding;
import com.loginform.model.ForgotPasswordModel;
import com.loginform.presenter.ForgotPasswordPresenter;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordContract.View{

    private ForgotPasswordMainBinding binding;
    private ForgotPasswordContract.Presenter presenter;
    private final String USERNAME_FIELD = "username";
    private final String NEW_PASSWORD_FIELD = "new_password";
    private final String CONFIRM_NEW_PASS_FIELD = "confirm_new_password";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ForgotPasswordMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ForgotPasswordPresenter(this, new ForgotPasswordModel());

        ViewCompat.setOnApplyWindowInsetsListener(binding.forgotPasswordMain, ((v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }));

        binding.loginRedirect.setOnClickListener(v -> onClickLoginRedirect());
        binding.changePasswordBtn.setOnClickListener(v -> onClickChangePassBtn());
    }

    private void onClickChangePassBtn() {
        String username = binding.username.getText().toString();
        String newPassword = binding.password.getText().toString();
        String confirmPassword = binding.confirmPassword.getText().toString();

        presenter.handleOnClickChangePassBtn(username, newPassword, confirmPassword);
    }

    private void onClickLoginRedirect() {
        presenter.handleOnClickLoginRedirectBtn();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(ForgotPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorField(String field, String type) {
        switch (field) {
            case USERNAME_FIELD:
                binding.username.setError(type);
                break;
            case NEW_PASSWORD_FIELD:
                binding.password.setError(type);
                break;
            case CONFIRM_NEW_PASS_FIELD:
                binding.confirmPassword.setError(type);
        }
    }

    @Override
    public void redirectLoginActivity() {
        //Start MainActivity
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
