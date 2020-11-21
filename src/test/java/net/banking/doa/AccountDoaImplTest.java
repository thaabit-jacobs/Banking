package net.banking.doa;

import net.banking.doa.account.AccountDoaImpl;
import net.banking.doa.user.UserDoaImpl;
import net.banking.models.Account;
import net.banking.types.AccountType;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountDoaImplTest {
    private final AccountDoaImpl accountDoaImpl = new AccountDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/bankingtest", "thaabit", "1234"));
    private final Account account = new Account(1, UUID.fromString("cc23148a-7c53-4842-8524-5ebd151eeadd"), AccountType.SAVINGS.toString(), 100, LocalDateTime.now(), 1);

    @Test
    void shouldReturnTRueWhenSuccesfullyAddingAccount(){
        accountDoaImpl.deleteAllAccounts();
        assertTrue(accountDoaImpl.insertAccount(account));
    }

    @Test
    void shouldReturnAccountObjectForValidId(){
        accountDoaImpl.deleteAllAccounts();
        accountDoaImpl.insertAccount(account);
        assertEquals(1, accountDoaImpl.selectAccount(1).getId());
    }

    @Test
    void shouldReturnListOfAllAccounts(){
        accountDoaImpl.deleteAllAccounts();
        accountDoaImpl.insertAccount(account);
        assertEquals(1, accountDoaImpl.selectAllAccount().size());
    }

    @Test
    void shouldReturnTRueWhenUpdatingAccount(){
        accountDoaImpl.deleteAllAccounts();
        accountDoaImpl.insertAccount(account);
        account.setAccountBalance(300);
        assertTrue(accountDoaImpl.updateAccount(account));
        assertEquals(300, accountDoaImpl.selectAccount(1).getAccountBalance());
    }
}
