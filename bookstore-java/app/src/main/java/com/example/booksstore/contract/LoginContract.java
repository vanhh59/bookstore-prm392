package com.example.booksstore.contract;

import com.loginform.model.Account;

public interface LoginContract {
    interface View {
        void showToastMessage(String message);
        void showErrorField(String field, String type);
        void redirectRegisterActivity();
        void redirectMainActivity();
        void redirectForgotPasswordActivity();
    }

    interface Model {
        Account getAccount(String username, String password);
    }

    interface Presenter {
        void handleOnClickRegisterBtn();
        void handleOnClickLoginBtn(String username, String password);
        void handleOnClickForgotPassword();
    }
}
