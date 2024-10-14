package com.example.booksstore.model;

import com.loginform.contract.ForgotPasswordContract;

public class ForgotPasswordModel implements ForgotPasswordContract.Model {

    private AccountDAOImpl dao;

    public ForgotPasswordModel() {
        this.dao = AccountDAOImpl.getInstance();
    }


    @Override
    public boolean updatePassword(String username, String password) {
        return dao.update(username, password);
    }
}
