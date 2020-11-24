package net.banking.doa.transacs;

import net.banking.models.Account;
import net.banking.models.Transaction;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;

public interface TransactionDoa {

    @SqlUpdate("insert into transacs (id, transacs_type, transacs_amount, is_success, date_created, account_id)" +
            "values(:id, :transactionType, :transactionAmount, :isSuccess, :dateCreated, :accountId)")
    boolean insertTransaction(@BindBean Transaction transaction);

    @SqlQuery("select * from transacs where id=:id")
    Transaction selectTransaction(@Bind("id") int id);

    @SqlQuery("select * from transacs where account_id=:accountId")
    List<Transaction> selectAllTransactionForAccount(@Bind("accountId") int accountId);

    @SqlQuery("select * from transacs")
    List<Transaction> selectAllTransactions();

    @SqlUpdate("delete from transacs where id=:id")
    boolean deleteTransaction(@Bind("id") int id);

    @SqlUpdate("delete from transacs")
    boolean deleteAllTransactions();

    @SqlQuery("select * from transacs where account_id=:id and date_created::date BETWEEN :dateOne and :dateTwo")
    public List<Transaction> selectAllTransactionBetween(@Bind("dateOne") LocalDate dateOne, @Bind("dateTwo") LocalDate  dateTwo, @Bind("id") int id);
}
