package net.banking.service;

import net.banking.doa.transacs.TransactionDoa;
import net.banking.doa.transacs.TransactionDoaImpl;
import net.banking.models.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionDoa transactionDoa = new TransactionDoaImpl();

    public boolean insertTransaction(Transaction transaction){
        return transactionDoa.insertTransaction(transaction);
    }

    public Transaction selectTransaction(int id){
        return transactionDoa.selectTransaction(id);
    }

    public List<Transaction> selectAllTransactions(){
        return transactionDoa.selectAllTransactions();
    }

    public boolean deleteTransaction(int id){
        return transactionDoa.deleteTransaction(id);
    }
}
