package com.eteration.simplebanking.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String owner;
    private String accountNumber;
    private double balance;
    private LocalDateTime createDate;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public Account() {

    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) throws InsufficientBalanceException {
        if (balance < amount) {
            throw new InsufficientBalanceException("insufficient balance");
        }
        balance -= amount;
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        balance += transaction.calculateBalance();
        if (balance < 0) {
            throw new InsufficientBalanceException("insufficient balance");
        }
        getTransactions().add(transaction);
        transaction.setAccount(this);
    }
}
