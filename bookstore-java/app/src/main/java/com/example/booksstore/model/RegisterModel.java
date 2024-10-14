package com.example.booksstore.model;

import com.loginform.contract.RegisterContract;

public class RegisterModel implements RegisterContract.Model {

    private AccountDAOImpl dao;

    public RegisterModel() {
        this.dao = AccountDAOImpl.getInstance();
    }

    @Override
    public boolean saveAccount(String username, String password) {
        return dao.save(new Account(username, password));
    }
}
