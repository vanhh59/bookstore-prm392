package com.example.booksstore.contract;

public interface ForgotPasswordContract {

    interface View {
        void showToastMessage(String message);
        void showErrorField(String field, String type);
        void redirectLoginActivity();
    }

    interface Model {
        boolean updatePassword(String username, String password);
    }

    interface Presenter {
        void handleOnClickChangePassBtn(String username, String password, String confirmPassword);
        void handleOnClickLoginRedirectBtn();
    }
}
