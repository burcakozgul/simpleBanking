package com.eteration.simplebanking.services;

import java.time.LocalDateTime;
import java.util.UUID;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionStatus createAccount(Account account) {
        TransactionStatus transactionStatus;
        if (accountRepository.getByAccountNumber(account.getAccountNumber()) != null) {
            transactionStatus = new TransactionStatus("NOK");
        } else {
            account.setCreateDate(LocalDateTime.now());
            accountRepository.save(account);
            transactionStatus = new TransactionStatus("OK");
        }
        return transactionStatus;
    }

    public Account findAccount(String accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber);
    }

    public TransactionStatus credit(String accountNumber, DepositTransaction depositTransaction) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        TransactionStatus transactionStatus;
        if (account != null) {
            Transaction transaction =
                new DepositTransaction(LocalDateTime.now(), depositTransaction.getAmount(), account, UUID.randomUUID().toString());
            account.post(transaction);
            transactionRepository.save(transaction);
            accountRepository.save(account);
            transactionStatus = new TransactionStatus("OK", transaction.getApprovalCode());
        } else {
            transactionStatus = new TransactionStatus("NOK");
        }
        return transactionStatus;
    }

    public TransactionStatus debit(String accountNumber, WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        TransactionStatus transactionStatus;
        if (account != null) {
            Transaction transaction =
                new WithdrawalTransaction(LocalDateTime.now(), withdrawalTransaction.getAmount(), account, UUID.randomUUID().toString());
            account.post(transaction);
            transactionRepository.save(transaction);
            accountRepository.save(account);
            transactionStatus = new TransactionStatus("OK", transaction.getApprovalCode());
        } else {
            transactionStatus = new TransactionStatus("NOK");
        }
        return transactionStatus;
    }


    public TransactionStatus payBill(String accountNumber, BillPaymentTransaction billPaymentTransaction) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        TransactionStatus transactionStatus;
        if (account != null) {
            Transaction transaction =
                new BillPaymentTransaction(LocalDateTime.now(), billPaymentTransaction.getAmount(), account, UUID.randomUUID().toString(),
                    billPaymentTransaction.getPayee(), billPaymentTransaction.getNumber());
            account.post(transaction);
            transactionRepository.save(transaction);
            accountRepository.save(account);
            transactionStatus = new TransactionStatus("OK", transaction.getApprovalCode());
        } else {
            transactionStatus = new TransactionStatus("NOK");
        }
        return transactionStatus;
    }
}
