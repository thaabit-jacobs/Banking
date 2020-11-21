package net.banking.action;

import net.banking.exceptions.InsufficientFundsException;
import net.banking.models.Account;
import net.banking.types.AccountType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionActionTest {


    private TransactionAction transactionAction = TransactionAction.getInstance();
    private Account accountOne = new Account(1,"3555426104730148", AccountType.SAVINGS.toString(), 150.00, LocalDateTime.now(), 1);

    @Test
    void shouldReturnATransactionObjectAndReturnBalanceFromAccount(){
        assertEquals(150.00, transactionAction.balanceEnquiry(accountOne).getTransactionAmount());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForAmountLessThan0(){
        try{
            transactionAction.withDrawal(accountOne, -50);
        }catch (InsufficientFundsException e){
            assertFalse(false);
        }catch(IllegalArgumentException ie){
            assertTrue(true);
        }
    }

    @Test
    void shouldInsuffucientFundsExceptionForAccountBalanceLessThanAnount(){
        try{
            transactionAction.withDrawal(accountOne, 200);
        }catch (InsufficientFundsException e){
            assertTrue(true);
        }catch(IllegalArgumentException ie){
            assertFalse(false);
        }
    }

    @Test
    void shouldReturnTransactionObjectWithWithDrawalAmountAndDeductFromAccountBanlance(){
        try{
            assertEquals(100, transactionAction.withDrawal(accountOne, 100).getTransactionAmount());
            assertEquals(50, accountOne.getBalance());
        }catch (InsufficientFundsException e){
            assertTrue(false);
        }catch(IllegalArgumentException ie){
            assertFalse(false);
        }
    }

    @Test
    void shouldIllgelaArgumentExceptionForAmountLess0(){
        try{
            transactionAction.deposit(accountOne, -150);
        }catch(IllegalArgumentException ie){
            assertTrue(true);
        }
    }

    @Test
    void shouldReturnTRansactionWithDepositAmountAndAddToAccountBalance(){
        assertEquals(100, transactionAction.deposit(accountOne, 100).getTransactionAmount());
        assertEquals(250, accountOne.getBalance());
    }
}
