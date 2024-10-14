package com.example.booksstore.contract;

public interface RegisterContract {
    interface View {
        void showToastMessage(String message);
        void showErrorField(String field, String type);
        void redirectLoginActivity();
    }

    interface Model {
        boolean saveAccount(String username, String password);
    }

    interface Presenter {
        void handleOnClickLoginRedirectBtn();
        void handleOnClickRegisterBtn(String username, String password, String confirmPassword);
    }
}
