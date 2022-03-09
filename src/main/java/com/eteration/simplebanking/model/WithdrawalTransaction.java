package com.eteration.simplebanking.model;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction {

    private double amount;

    public WithdrawalTransaction(double amount) {
        super(amount);
        this.amount = amount;
    }

    public WithdrawalTransaction() {

    }

    public WithdrawalTransaction(LocalDateTime date, double amount, Account account, String approvalCode) {
        super(date, amount, account, approvalCode);
        this.amount = amount;
    }

    @Override
    public double calculateBalance() {
        return -amount;
    }
}


