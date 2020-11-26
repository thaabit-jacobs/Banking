package net.banking.doa;

import net.banking.doa.account.AccountDoaImpl;
import net.banking.doa.transacs.TransactionDoaImpl;
import net.banking.models.Account;
import net.banking.models.Transaction;
import net.banking.models.User;
import net.banking.service.AccountService;
import net.banking.service.UserService;
import net.banking.types.AccountType;
import net.banking.types.TransactionType;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionDoaImplTest {
    private final TransactionDoaImpl transactionDoa = new TransactionDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/bankingtest", "thaabit", "1234"));
    private final Transaction transac = new Transaction(1, TransactionType.WITHDRAWAL.toString(), 50.00, true, LocalDateTime.now(), 1);
    private final UserService userService = new UserService();
    private final AccountService accountService = new AccountService();

    private TransactionDoaImplTest() throws URISyntaxException, SQLException {
        userService.insertUser(new User(1, "thaabit", "Jacobs", "jacobs@java.com", LocalDateTime.now()));
    }

    @Test
    void shouldReturnTrueWhenAddingNewTRansactionToDb(){
        transactionDoa.deleteAllTransactions();
        accountService.deleteAllAccounts();
        userService.deleteUser(1);
        assertTrue(transactionDoa.insertTransaction(transac));
        ;
    }

    @Test
    void shouldReturnTransactionObjectForValidId(){
        transactionDoa.deleteAllTransactions();
        accountService.deleteAllAccounts();
        userService.deleteUser(1);
        transactionDoa.insertTransaction(transac);
        assertEquals(1, transactionDoa.selectTransaction(1).getId());
    }

    @Test
    void shouldReturnAListOfTransactions(){
        transactionDoa.deleteAllTransactions();
        accountService.deleteAllAccounts();
        userService.deleteUser(1);
        transactionDoa.insertTransaction(transac);
        assertEquals(1, transactionDoa.selectAllTransactions().size());
    }
}
