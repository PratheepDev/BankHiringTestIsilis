package com.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private final String name;

    private final List<Account> accounts = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
