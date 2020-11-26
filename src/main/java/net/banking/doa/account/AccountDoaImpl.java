package net.banking.doa.account;

;
import net.banking.mappers.AccountMapper;
import net.banking.mappers.UserMapper;
import net.banking.models.Account;
import net.banking.service.AccountService;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.OutParameter;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AccountDoaImpl implements AccountDoa {
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/banking", "thaabit", "1234");

    public AccountDoaImpl() throws URISyntaxException, SQLException {
        jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking");
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
    }

    public AccountDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new AccountMapper());
    }

    public AccountDoaImpl(String url){
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new AccountMapper());
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
    public List<Account> selectAllAccountsBetween(String dateOne, String dateTwo){
        jdbi.registerRowMapper(new AccountMapper());
        return jdbi.withExtension(AccountDoa.class, doa -> doa.selectAllAccountsBetween(dateOne, dateTwo));
    }

    @Override
    public List<Account> selectAccountForUser(int userId){
        jdbi.registerRowMapper(new AccountMapper());
        return jdbi.withExtension(AccountDoa.class, doa -> doa.selectAccountForUser(userId));
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

    public int getUniqueId(){
        List<Integer> results = jdbi.withHandle(handle -> {
           return  handle.createQuery("select * from nextval('accountsequence')")
                   .mapTo(int.class)
                   .list();

        });

        return results.get(0).intValue();
    }

}
