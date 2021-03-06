package net.banking.doa.account;

import net.banking.models.Account;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.UUID;

public interface AccountDoa {

    @SqlUpdate("insert into accounts (id, account_number, account_type, balance, date_created, user_id) " +
            "values(:id, :accountNumber, :accountType, :accountBalance, :dateCreated, :userId)")
    public boolean insertAccount(@BindBean Account account);

    @SqlQuery("select * from accounts where id=:id")
    public Account selectAccount(@Bind("id") int id);

    @SqlQuery("select * from accounts where account_number=:accountNumber")
    public Account selectAccount(@Bind("accountNumber") UUID accountNumber);

    @SqlQuery("select * from accounts order by id")
    public List<Account> selectAllAccount();

    @SqlQuery("select * from accounts where date_created between :dateOne and dateTwo")
    public List<Account> selectAllAccountsBetween(@Bind("dateOne") String dateOne, @Bind("dateTwo") String dateTwo);

    @SqlQuery("select * from accounts where user_id=:userId")
    public List<Account> selectAccountForUser(@Bind("userId") int userId);

    @SqlUpdate("update accounts set balance=:accountBalance where id=:id")
    public boolean updateAccount(@BindBean Account account);

    @SqlUpdate("delete from accounts where id=:id")
    public boolean deleteAccount(@Bind("id") int id);

    @SqlUpdate("delete from accounts")
    public boolean deleteAllAccounts();
}
