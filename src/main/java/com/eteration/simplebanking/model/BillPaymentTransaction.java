package com.eteration.simplebanking.model;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("BillPaymentTransaction")
public class BillPaymentTransaction extends Transaction {

    private String payee;
    private String number;
    private double amount;

    public BillPaymentTransaction(String payee, String number, double amount) {
        super(amount);
        this.payee = payee;
        this.number = number;
        this.amount = amount;
    }

    public BillPaymentTransaction(LocalDateTime date, double amount, Account account, String approvalCode, String payee, String number) {
        super(date, amount, account, approvalCode);
        this.payee = payee;
        this.number = number;
        this.amount = amount;
    }

    public BillPaymentTransaction() {

    }

    @Override
    public double calculateBalance() {
        return -amount;
    }
}
