
package net.banking.action;

import net.banking.exceptions.InsufficientFundsException;
import net.banking.models.Account;
import net.banking.models.Transaction;
import net.banking.types.TransactionType;

import java.time.LocalDateTime;

public class TransactionAction {
    private static final TransactionAction instance = new TransactionAction();

    private TransactionAction(){}

    public static TransactionAction getInstance(){
        return instance;
    }

    public synchronized Transaction balanceEnquiry(Account account){
        return new Transaction()
                .setIsSuccess(true)
                .setTransactionAmount(account.getAccountBalance())
                .setTransactionType(TransactionType.BALANCE_ENQUIRY.toString())
                .setDateCreated(LocalDateTime.now())
                .setAccountId(account.getId())
                .build();
    }

    public synchronized Transaction withDrawal(Account account, double amount)throws InsufficientFundsException{
        if(amount <= 0)
            throw new IllegalArgumentException("invalid amount supplied");

        if(account.getAccountBalance() < amount){
            throw new InsufficientFundsException();
        }


        double newBalance = account.getAccountBalance() - amount;
        account.setAccountBalance(newBalance);

        return new Transaction()
                .setIsSuccess(true)
                .setTransactionAmount(amount)
                .setTransactionType(TransactionType.WITHDRAWAL.toString())
                .setDateCreated(LocalDateTime.now())
                .setAccountId(account.getId())
                .build();
    }

    public synchronized Transaction deposit(Account account, double amount){
        if(amount <= 0)
            throw new IllegalArgumentException("invalid amount supplied");

        double newBalance = account.getAccountBalance() + amount;
        account.setAccountBalance(newBalance);

        return new Transaction()
                .setIsSuccess(true)
                .setTransactionAmount(amount)
                .setTransactionType(TransactionType.DEPOSIT.toString())
                .setDateCreated(LocalDateTime.now())
                .setAccountId(account.getId())
                .build();
    }
}

