package net.banking.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Transaction {
    private int id;
    private String transactionType;
    private double transactionAmount;
    private boolean isSuccess;
    private LocalDateTime dateCreated;
    private int accountId;

    public Transaction(){

    }

    public Transaction(int id, String transactionType, double transactionAmount, boolean isSuccess, LocalDateTime dateCreated, int accountId) {
        if (id < 0)
            throw new IllegalArgumentException("invalid id supplied " + id);
        this.id = id;

        this.transactionType = transactionType;

        this.transactionAmount = transactionAmount;

        this.isSuccess = isSuccess;

        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;

        if (accountId < 0)
            throw new IllegalArgumentException("invalid accountId supplied " + accountId);
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public Transaction setId(int id){
        if (id < 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;

        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Transaction setTransactionType(String transactioType){
        if(transactioType == null || transactioType.trim().length() == 0)
            throw new IllegalArgumentException("invalid transactioType supplied");
        this.transactionType = transactioType;

        return this;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public Transaction setTransactionAmount(double transactionAmount){
        this.transactionAmount = transactionAmount;

        return this;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public Transaction setIsSuccess(boolean success) {
        isSuccess = success;

        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public Transaction setDateCreated(LocalDateTime dateCreated){
        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;

        return this;
    }

    public int getAccountId() {
        return accountId;
    }

    public Transaction setAccountId(int accountId){
        if (accountId < 0)
            throw new IllegalArgumentException("invalid accountId supplied");
        this.accountId = accountId;

        return this;
    }

    public Transaction build(){
        return new Transaction(id, transactionType, transactionAmount, isSuccess, dateCreated, accountId);
    }

    @Override
    public String toString() {
        return "Transaction{ \n" +
                " id=" + id +
                ", \n transactioType='" + transactionType + '\'' +
                ", \n transactionAmount=" + transactionAmount +
                ", \n isSuccess=" + isSuccess +
                ", \n dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  Transaction))
            return false;

        Transaction transaction = (Transaction)o;
        return this.id == transaction.id && this.dateCreated == transaction.dateCreated;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
