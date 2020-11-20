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

    public Transaction(){

    }

    public Transaction(int id, String transactioType, double transactionAmount, boolean isSuccess, LocalDateTime dateCreated) {
        if (id <= 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;

        this.transactioType = transactioType;

        if (transactionAmount < 0)
            throw new IllegalArgumentException("invalid amount supplied");
        this.transactionAmount = transactionAmount;

        this.isSuccess = isSuccess;

        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        if (id <= 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;
    }

    public String getTransactioType() {
        return transactioType;
    }

    public void setTransactioType(String transactioType){
        if(transactioType == null || transactioType.trim().length() == 0)
            throw new IllegalArgumentException("invalid transactioType supplied");
        this.id = id;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount){
        if (transactionAmount < 0)
            throw new IllegalArgumentException("invalid amount supplied");
        this.transactionAmount = transactionAmount;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated){
        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;
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