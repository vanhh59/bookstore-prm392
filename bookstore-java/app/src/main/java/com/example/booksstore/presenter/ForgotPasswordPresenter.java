package com.example.booksstore.presenter;

import com.loginform.contract.ForgotPasswordContract;

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    private ForgotPasswordContract.View view;
    private ForgotPasswordContract.Model model;
    private final String USERNAME_FIELD = "username";
    private final String PASSWORD_FIELD = "password";
    private final String CONFIRM_PASSWORD_FIELD = "confirm_password";
    private final String REQUIRE = "Require";

    public ForgotPasswordPresenter(ForgotPasswordContract.View view, ForgotPasswordContract.Model model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void handleOnClickChangePassBtn(String username, String password, String confirmPassword) {
        if (!checkEmpty(username, password, confirmPassword)) {
            view.showToastMessage("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            view.showToastMessage("Confirm password is not match.");
            return;
        }

        if (model.updatePassword(username, password)) {
            view.showToastMessage("Change password successfully.");
            view.redirectLoginActivity();
        }else {
            view.showToastMessage("Change password fail: username is not found.");
        }
    }

    @Override
    public void handleOnClickLoginRedirectBtn() {
        view.redirectLoginActivity();
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
