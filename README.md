# Getting Started with Simple Banking

Builded a banking service that can handle any number of transactions for bank accounts. The service is part of a larger collection of services that model the inner workings of a bank. The services for the "bank account" provide a simple model of how bank accounts might work in an overly simplified world.

The bank account is exclusively interested in maintaining the name of the account owner, the number of the account and the account’s balance. The endpoints limited to methods that provide a means of crediting and debiting the account. 

Data model for the bank account object have fields owner where the field type is java.lang.String, fields to hold the account number (String) and balance (double). the credit() service as specified above adds the supplied amount to the receiving BankAccounts balance and the the debit() service subtracts the supplied amount from the receiving BankAccounts balance.  

The object model for our banking system includes transaction objects. A transaction object keeps track of the kind of transaction (deposit, withdrawal, payments etc.) as well as the date and amount of the transaction. Each transaction type has its own parameters. The following diagram shows how BankAccounts and Transactions are related. 

![model](images/model.png)

## Rest Call Examples

To deposit money:

    curl --location --request POST 'http://localhost:8080/account/v1/credit/669-7788' \
    --header 'Content-Type: application/json' \
    --header 'Accept: application/json' \
    --data-raw '    {
            "amount": 1000.0
        }'

    response would be (200):
    {
        "status": "OK",
        "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
    }

To withdraw money:

    curl --location --request POST 'http://localhost:8080/account/v1/debit/669-7788' \
    --header 'Content-Type: application/json' \
    --header 'Accept: application/json' \
    --data-raw '    {
            "amount": 50.0
        }'

    response would be (200):
    {
        "status": "OK",
        "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
    }

To get the current account data:

    curl --location --request GET 'http://localhost:8080/account/v1/669-7788'

    response would be:

    {
        "accountNumber": "669-7788",
        "owner": "Kerem Karaca",
        "balance": 950.0,
        "createDate": "2020-03-26T06:15:50.550+0000",
        "transactions": [
            {
                "date": "2020-03-26T06:16:03.563+0000",
                "amount": 1000.0,
                "type": "DepositTransaction",
                "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
            },
            {
                "date": "2020-03-26T06:16:35.047+0000",
                "amount": 50.0,
                "type": "WithdrawalTransaction",
                "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
            }
        ]
    }


