package net.banking.doa.account;

import net.banking.mappers.AccountMapper;
import net.banking.mappers.UserMapper;
import net.banking.models.Account;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;
import java.util.UUID;

public class AccountDoaImpl implements AccountDoa {
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/banking", "thaabit", "1234");

    public AccountDoaImpl(){
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
    }

    public AccountDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new AccountMapper());
    }

    @Override
    public boolean insertAccount(Account account){
        return jdbi.withExtension(AccountDoa.class, doa -> doa.insertAccount(account));
    }

    @Override
    public Account selectAccount(int id){

        jdbi.registerRowMapper(new AccountMapper());return jdbi.withExtension(AccountDoa.class, doa -> doa.selectAccount(id));
    }

    @Override
    public Account selectAccount(UUID accountNumber){

        jdbi.registerRowMapper(new AccountMapper());return jdbi.withExtension(AccountDoa.class, doa -> doa.selectAccount(accountNumber));
    }

    @Override
    public List<Account> selectAllAccount(){
        jdbi.registerRowMapper(new AccountMapper());
        return jdbi.withExtension(AccountDoa.class, doa -> doa.selectAllAccount());
    }

    @Override
    public boolean updateAccount(Account account){
        return jdbi.withExtension(AccountDoa.class, doa -> doa.updateAccount(account));
    }

    @Override
    public boolean deleteAccount(int id){
        return jdbi.withExtension(AccountDoa.class, doa -> doa.deleteAccount(id));
    }

    @Override
    public boolean deleteAllAccounts(){
        return jdbi.withExtension(AccountDoa.class, doa -> doa.deleteAllAccounts());
    }
}
