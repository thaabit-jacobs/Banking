package net.banking.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Transaction {
    private int id;
    private String transactioType;
    private double transactionAmount;
    private boolean isSuccess;
    private LocalDateTime dateCreated;
    private int accountId;

    public Transaction(){

    }

    public Transaction(int id, String transactioType, double transactionAmount, boolean isSuccess, LocalDateTime dateCreated, int accountId) {
        if (id < 0)
            throw new IllegalArgumentException("invalid id supplied " + id);
        this.id = id;

        this.transactioType = transactioType;

        if (transactionAmount < 0)
            throw new IllegalArgumentException("invalid amount supplied");
        this.transactionAmount = transactionAmount;

        this.isSuccess = isSuccess;

        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;

        if (accountId < 0)
            throw new IllegalArgumentException("invalid accountId supplied " + id);
        this.id = id;
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

    public String getTransactioType() {
        return transactioType;
    }

    public Transaction setTransactioType(String transactioType){
        if(transactioType == null || transactioType.trim().length() == 0)
            throw new IllegalArgumentException("invalid transactioType supplied");
        this.id = id;

        return this;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public Transaction setTransactionAmount(double transactionAmount){
        if (transactionAmount < 0)
            throw new IllegalArgumentException("invalid amount supplied");
        this.transactionAmount = transactionAmount;

        return this;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Transaction setSuccess(boolean success) {
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
        return new Transaction(id, transactioType, transactionAmount, isSuccess, dateCreated, accountId);
    }

    @Override
    public String toString() {
        return "Transaction{ \n" +
                " id=" + id +
                ", \n transactioType='" + transactioType + '\'' +
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
