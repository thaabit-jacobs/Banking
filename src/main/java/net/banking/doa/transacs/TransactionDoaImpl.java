package net.banking.doa.transacs;

import net.banking.mappers.AccountMapper;
import net.banking.mappers.TransactionMapper;
import net.banking.models.Transaction;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class TransactionDoaImpl implements TransactionDoa {

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/banking", "thaabit", "1234");

    public TransactionDoaImpl(){
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
    }

    public TransactionDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new TransactionMapper());
    }

    @Override
    public boolean insertTransaction(Transaction transaction){
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.insertTransaction(transaction));
    }

    @Override
    public Transaction selectTransaction(int id){
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectTransaction(id));
    }

    @Override
    public List<Transaction> selectAllTransactions(){
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectAllTransactions());
    }

    @Override
    public boolean deleteTransaction(int id){
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.deleteTransaction(id));
    }

    @Override
    public boolean deleteAllTransactions(){
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.deleteAllTransactions());
    }
}
