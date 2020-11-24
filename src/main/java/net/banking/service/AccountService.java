package net.banking.service;

import net.banking.doa.account.AccountDoaImpl;
import net.banking.models.Account;

import java.util.Collections;
import java.util.Comparator;
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

    public List<Account> selectAllAccountsBetween(String dateOne, String dateTwo){
        return accountDoa.selectAllAccountsBetween(dateOne, dateTwo);
    }


    public int getNewAccountId(){
        List<Account> accountList = selectAccount();

        if(selectAccount().size() == 0)
            return 1;

        Comparator<Account> byId = (a1, a2) -> a1.getId() - a2.getId();

        Collections.sort(accountList, byId);

        int newId = accountList.get(accountList.size() - 1).getId()  + 1;
        return newId;
    }
}
