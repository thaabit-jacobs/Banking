package net.banking.types;

public enum AccountType {
    SAVINGS("Savings") {
        public String getDescription(){
            return "an interest-bearing deposit account";
        }
    },

    CHEQUE("Cheque"){
        public String getDescription(){
            return "a deposit account that allows for withdrawals through checks, automated teller machines, or debit cards.";
        }
    },

    DEPOSIT("Deposit"){
        public String getDescription(){
            return "pays interest and is usually not able to be drawn on without notice or loss of interest. pays interest and is usually not able to be drawn on without notice or loss of interest.";
        }
    };

    private String description;

    private AccountType(String description){
        this.description = description;
    }

    public abstract String getDescription();
}
