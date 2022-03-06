package com.eteration.simplebanking.model;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type" , discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private double amount;
    @Column(name = "transaction_type", insertable = false, updatable = false)
    private String type;
    private String approvalCode;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(double amount) {
        this.amount = amount;
        this.setDate(LocalDateTime.now());
    }

    public Transaction() {
    }

    public Transaction(LocalDateTime date, double amount, Account account, String approvalCode) {
        this.date = date;
        this.amount = amount;
        this.account = account;
        this.approvalCode = approvalCode;
    }
}
