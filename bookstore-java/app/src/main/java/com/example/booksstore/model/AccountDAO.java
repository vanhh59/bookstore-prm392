package com.example.booksstore.model;

import java.util.List;

public interface AccountDAO {
    boolean save(Account account);
    List<Account> getAccountList();
    boolean exist(Account account);
    Account get(String username, String password);
    boolean update(String username, String password);
}
