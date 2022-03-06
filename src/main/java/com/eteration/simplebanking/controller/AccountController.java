package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.BillPaymentTransaction;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.TransactionStatus;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<TransactionStatus> createAccount(@RequestBody Account account) {
        TransactionStatus transactionStatus = accountService.createAccount(account);
        return ResponseEntity.ok().body(transactionStatus);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction)
        throws InsufficientBalanceException {
        TransactionStatus transactionStatus = accountService.credit(accountNumber, depositTransaction);
        return ResponseEntity.ok().body(transactionStatus);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction)
        throws InsufficientBalanceException {
        TransactionStatus transactionStatus = accountService.debit(accountNumber, withdrawalTransaction);
        return ResponseEntity.ok().body(transactionStatus);
    }

    @PostMapping("/payBill/{accountNumber}")
    public ResponseEntity<TransactionStatus> payBill(@PathVariable String accountNumber, @RequestBody BillPaymentTransaction billPaymentTransaction)
        throws InsufficientBalanceException {
        TransactionStatus transactionStatus = accountService.payBill(accountNumber, billPaymentTransaction);
        return ResponseEntity.ok().body(transactionStatus);
    }
}