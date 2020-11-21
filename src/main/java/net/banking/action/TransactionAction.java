package net.banking.action;

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


}
