package com.example.booksstore.model;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO{

    private static AccountDAOImpl instance;
    private static List<Account> accounts = new ArrayList<>();

    private AccountDAOImpl() {

    }

    public static AccountDAOImpl getInstance() {
        if (instance == null) {
            instance = new AccountDAOImpl();
        }

        return instance;
    }

    @Override
    public boolean save(Account account) {
        if (exist(account)) {
            return false;
        }

        accounts.add(account);
        return true;
    }

    @Override
    public List<Account> getAccountList() {
        return accounts;
    }

    @Override
    public boolean exist(Account account) {
        return accounts.contains(account);
    }

    @Override
    public Account get(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)
                    && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean update(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                account.setPassword(password);
                return true;
            }
        }
        return false;
    }
}
