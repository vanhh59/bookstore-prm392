package com.example.booksstore.presenter;

import com.loginform.contract.LoginContract;
import com.loginform.model.Account;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.Model model;
    private LoginContract.View view;
    private final String USERNAME_FIELD = "username";
    private final String PASSWORD_FIELD = "password";
    private final String REQUIRE = "Require";

    public LoginPresenter(LoginContract.Model model, LoginContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handleOnClickRegisterBtn() {
        view.redirectRegisterActivity();
    }

    @Override
    public void handleOnClickLoginBtn(String username, String password) {
        if (!checkEmpty(username, password)) {
            view.showToastMessage("Please fill in all fields.");
        }else {
            Account account = model.getAccount(username, password);
            if (account != null) {
                view.showToastMessage("Login successfully.");
                view.redirectMainActivity();
            }else {
                view.showToastMessage("Login fail: username or password is incorrect.");
            }
        }
    }

    private boolean checkEmpty(String username, String password) {
        boolean flag = true;

        if (username.isEmpty()) {
            view.showErrorField(USERNAME_FIELD, REQUIRE);
            flag = false;
        }

        if (password.isEmpty()) {
            view.showErrorField(PASSWORD_FIELD, REQUIRE);
            flag = false;
        }

        return flag;
    }

    @Override
    public void handleOnClickForgotPassword() {
        view.redirectForgotPasswordActivity();
    }
}
