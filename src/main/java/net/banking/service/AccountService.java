package net.banking.service;

import net.banking.doa.account.AccountDoaImpl;
import net.banking.models.Account;

import java.util.List;
import java.util.UUID;

public class AccountService {
    private final AccountDoaImpl accountDoa = new AccountDoaImpl();

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


    public int getNewAccountId(){
        if(selectAccount().size() == 0)
            return 1;

        return selectAccount().get(selectAccount().size() - 1).getId() + 1;
    }
}
