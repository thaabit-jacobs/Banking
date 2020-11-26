package net.banking.service;

import net.banking.doa.account.AccountDoaImpl;
import net.banking.models.Account;
import net.banking.models.Transaction;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

public class AccountService {
    private final AccountDoaImpl accountDoa = new AccountDoaImpl();

    public AccountService() throws URISyntaxException, SQLException {
    }

    public boolean insertAccount(Account account){
        return accountDoa.insertAccount(account);
    }

    public Account selectAccount(int id){
        return accountDoa.selectAccount(id);
    }

    public Account selectAccount(UUID accountNumber){
        return accountDoa.selectAccount(accountNumber);
    }

    public List<Account> selectAccount(){
        return accountDoa.selectAllAccount();
    }

    public List<Account> selectAccountForUser(int userId){
        return accountDoa.selectAccountForUser(userId);
    }

    public boolean updateAccount(Account account){
        return accountDoa.updateAccount(account);
    }

    public boolean deleteAccount(int id){
        return accountDoa.deleteAccount(id);
    }

    public boolean deleteAllAccounts(){
        return accountDoa.deleteAllAccounts();
    }

    public List<Account> selectAllAccountsBetween(String dateOne, String dateTwo){
        return accountDoa.selectAllAccountsBetween(dateOne, dateTwo);
    }

    public int getNewAccountId(){
        return accountDoa.getUniqueId();
    }
}
