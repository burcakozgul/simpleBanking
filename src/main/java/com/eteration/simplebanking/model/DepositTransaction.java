package com.eteration.simplebanking.model;


import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount);
    }

    public DepositTransaction() {
    }

    public DepositTransaction(LocalDateTime date, double amount, Account account, String approvalCode) {
        super(date, amount, account, approvalCode);
    }
}
