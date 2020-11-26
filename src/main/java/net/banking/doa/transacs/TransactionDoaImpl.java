package net.banking.doa.transacs;

import net.banking.mappers.AccountMapper;
import net.banking.mappers.TransactionMapper;
import net.banking.models.Transaction;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.customizer.Bind;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TransactionDoaImpl implements TransactionDoa {

    private Jdbi jdbi;

    public TransactionDoaImpl() throws URISyntaxException, SQLException {
        jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking");
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
    }

    public TransactionDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new TransactionMapper());
    }

    static Jdbi getJdbiDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return Jdbi.create(url, username, password);
        }

        return Jdbi.create(defualtJdbcUrl);

    }

    @Override
    public boolean insertTransaction(Transaction transaction){
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.insertTransaction(transaction));
    }

    @Override
    public Transaction selectTransaction(int id){
        jdbi.registerRowMapper(new TransactionMapper());
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectTransaction(id));
    }

    @Override
    public List<Transaction> selectAllTransactionForAccount(int accountId){
        jdbi.registerRowMapper(new TransactionMapper());
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectAllTransactionForAccount(accountId));
    }

    @Override
    public List<Transaction> selectAllTransactions(){
        jdbi.registerRowMapper(new TransactionMapper());
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

    @Override
    public List<Transaction> selectAllTransactionBetween(LocalDate dateOne, LocalDate  dateTwo, int id){
        jdbi.registerRowMapper(new TransactionMapper());
        return jdbi.withExtension(TransactionDoa.class, doa -> doa.selectAllTransactionBetween(dateOne, dateTwo, id));
    }

    public int getUniqueId(){
        List<Integer> results = jdbi.withHandle(handle -> {
            return  handle.createQuery("select * from nextval('transacequence')")
                    .mapTo(int.class)
                    .list();

        });

        return results.get(0).intValue();
    }
}
