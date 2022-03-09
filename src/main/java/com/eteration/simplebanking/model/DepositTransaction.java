package com.eteration.simplebanking.model;


import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    private double amount;

    public DepositTransaction(double amount) {
        super(amount);
        this.amount = amount;
    }

    public DepositTransaction() {
    }

    public DepositTransaction(LocalDateTime date, double amount, Account account, String approvalCode) {
        super(date, amount, account, approvalCode);
        this.amount = amount;
    }

    @Override
    public double calculateBalance() {
        return amount;
    }
}
