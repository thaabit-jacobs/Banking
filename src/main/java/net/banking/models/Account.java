package net.banking.models;

import java.time.LocalDateTime;

public class Account {
    private int id;
    private String accountNumber;
    private String accountType;
    private double balance;
    private LocalDateTime dateCreated;

    public Account(){

    }

    public Account(int id, String accountNumber, String accountType, double balance, LocalDateTime dateCreated) {
        if (id <= 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;

        if(accountNumber == null || accountNumber.trim().length() == 0)
            throw new IllegalArgumentException("invalid accountNumber supplied");
        this.accountNumber = accountNumber;

        if(accountType == null || accountType.trim().length() == 0)
            throw new IllegalArgumentException("invalid accountType supplied");
        this.accountType = accountType;

        if (balance < 0)
            throw new IllegalArgumentException("invalid balance supplied");
        this.balance = balance;


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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        if(accountNumber == null || accountNumber.trim().length() == 0)
            throw new IllegalArgumentException("invalid accountNumber supplied");
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance){
        if (balance < 0)
            throw new IllegalArgumentException("invalid balance supplied");
        this.balance = balance;
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
        return "Account{ \n" +
                "  id=" + id +
                ", \n accountNumber='" + accountNumber + '\'' +
                ", \n accountType='" + accountType + '\'' +
                ", \n balance=" + balance +
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
