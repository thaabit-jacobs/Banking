package net.banking.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private int id;
    private UUID accountNumber;
    private String accountType;
    private double accountBalance;
    private LocalDateTime dateCreated;
    private int userId;

    public Account(){

    }

    public Account(int id, UUID accountNumber, String accountType, double accountBalance, LocalDateTime dateCreated, int userId) {
        if (id <= 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;

        this.accountNumber = accountNumber;

        if(accountType == null || accountType.trim().length() == 0)
            throw new IllegalArgumentException("invalid accountType supplied");
        this.accountType = accountType;

        if (accountBalance < 0)
            throw new IllegalArgumentException("invalid balance supplied");
        this.accountBalance = accountBalance;


        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;

        if (userId <= 0)
            throw new IllegalArgumentException("invalid userId supplied");
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        if (id <= 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(UUID accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType){
        if(accountType == null || accountType.trim().length() == 0)
            throw new IllegalArgumentException("invalid accountType supplied");
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance){
        if (accountBalance < 0)
            throw new IllegalArgumentException("invalid balance supplied");
        this.accountBalance = accountBalance;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated){
        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId){
        if (userId <= 0)
            throw new IllegalArgumentException("invalid userId supplied");
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Account{ \n" +
                "  id=" + id +
                ", \n accountNumber='" + accountNumber + '\'' +
                ", \n accountType='" + accountType + '\'' +
                ", \n balance=" + accountBalance +
                ", \n dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(! (obj instanceof Account))
            return false;

        Account account = (Account)obj;
        return this.accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode(){
        return id;
    }
}
