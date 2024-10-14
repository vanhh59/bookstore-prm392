package com.example.booksstore.presenter;

import com.loginform.contract.RegisterContract;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private RegisterContract.Model model;
    private final String USERNAME_FIELD = "username";
    private final String PASSWORD_FIELD = "password";
    private final String CONFIRM_PASSWORD_FIELD = "confirm_password";
    private final String REQUIRE = "Require";

    public RegisterPresenter(RegisterContract.View view, RegisterContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handleOnClickLoginRedirectBtn() {
        view.redirectLoginActivity();
    }

    @Override
    public void handleOnClickRegisterBtn(String username, String password, String confirmPassword) {
        if (!checkEmpty(username, password, confirmPassword)) {
            view.showToastMessage("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            view.showToastMessage("Confirm password is not match.");
            return;
        }

        if (model.saveAccount(username, password)) {
            view.showToastMessage("Register successfully!");
            view.redirectLoginActivity();
        }else {
            view.showToastMessage("Register fail! Account is existed.");
        }
    }

    private boolean checkEmpty(String username, String password, String confirmPassword) {
        boolean flag = true;

        if (username.isEmpty()) {
            view.showErrorField(USERNAME_FIELD, REQUIRE);
            flag = false;
        }

        if (password.isEmpty()) {
            view.showErrorField(PASSWORD_FIELD, REQUIRE);
            flag = false;
        }

        if (confirmPassword.isEmpty()) {
            view.showErrorField(CONFIRM_PASSWORD_FIELD, REQUIRE);
            flag = false;
        }

        return flag;
    }
}
