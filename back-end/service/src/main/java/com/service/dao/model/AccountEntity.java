package com.service.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String code;
    
    @OneToOne
    private ClientEntity client;

    @Column
    private double balance = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ClientEntity getOwner() {
        return client;
    }

    public void setOwner(ClientEntity owner) {
        this.client = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
