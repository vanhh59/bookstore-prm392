package com.example.booksstore.model;

import com.loginform.contract.LoginContract;

public class LoginModel implements LoginContract.Model {

    private AccountDAOImpl dao;

    public LoginModel() {
        this.dao = AccountDAOImpl.getInstance();
    }

    @Override
    public Account getAccount(String username, String password) {
        return dao.get(username, password);
    }
}
