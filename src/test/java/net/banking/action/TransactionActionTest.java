package net.banking.action;

import net.banking.models.Account;
import net.banking.types.AccountType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionActionTest {


    private TransactionAction transactionAction = TransactionAction.getInstance();
    private Account accountOne = new Account(1,"3555426104730148", AccountType.SAVINGS.toString(), 0.00, LocalDateTime.now());

    @Test
    void shouldReturnATransactionObjectAndReturnBalanceFromAccount(){
        assertEquals(0.00, transactionAction.balanceEnquiry(accountOne).getTransactionAmount());
    }
}
