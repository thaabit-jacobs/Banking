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
                .setSuccess(true)
                .setTransactionAmount(account.getBalance())
                .setTransactioType(TransactionType.BALANCE_ENQUIRY.toString())
                .setDateCreated(LocalDateTime.now())
                .build();
    }

    public synchronized Transaction withDrawal(Account account, int amount)throws InsufficientFundsException{
        if(amount <= 0)
            throw new IllegalArgumentException("invalid amount supplied");

        if(account.getBalance() < amount){
            throw new InsufficientFundsException();
        }


        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);

        return new Transaction()
                .setSuccess(true)
                .setTransactionAmount(amount)
                .setTransactioType(TransactionType.WITHDRAWAL.toString())
                .setDateCreated(LocalDateTime.now())
                .build();
    }
}
