package net.banking.service;

import net.banking.doa.transacs.TransactionDoa;
import net.banking.doa.transacs.TransactionDoaImpl;
import net.banking.models.Transaction;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TransactionService {
    private final TransactionDoaImpl transactionDoa = new TransactionDoaImpl();

    public TransactionService() throws URISyntaxException, SQLException {
    }

    public boolean insertTransaction(Transaction transaction){
        return transactionDoa.insertTransaction(transaction);
    }

    public Transaction selectTransaction(int id){
        return transactionDoa.selectTransaction(id);
    }

    public List<Transaction> selectAllTransactionForAccount(int accountId){
        return transactionDoa.selectAllTransactionForAccount(accountId);
    }

    public List<Transaction> selectAllTransactions(){
        return transactionDoa.selectAllTransactions();
    }

    public boolean deleteTransaction(int id){
        return transactionDoa.deleteTransaction(id);
    }

    public int getNewId(){
        return transactionDoa.getUniqueId();
    }

    public List<Transaction> selectAllTransactionBetween(LocalDate dateOne, LocalDate  dateTwo, int id){
        return transactionDoa.selectAllTransactionBetween(dateOne, dateTwo, id);
    }
}
