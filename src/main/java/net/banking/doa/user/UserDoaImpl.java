package net.banking.doa.user;

import net.banking.mappers.UserMapper;
import net.banking.models.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDoaImpl implements UserDoa{

    private  Jdbi  jdbi;

    public UserDoaImpl() throws URISyntaxException, SQLException {
        jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking");
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
    }

    public UserDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new UserMapper());
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
    public boolean insertUser(User user){
        return jdbi.withExtension(UserDoa.class, doa -> doa.insertUser(user));
    }

    @Override
    public boolean updateUser(User user){
        return jdbi.withExtension(UserDoa.class, doa -> doa.updateUser(user));
    }

    @Override
    public boolean clearAllUsers(){
        return jdbi.withExtension(UserDoa.class, doa -> doa.clearAllUsers());
    }

    @Override
    public User selectUser(int id){
        jdbi.registerRowMapper(new UserMapper());
        return jdbi.withExtension(UserDoa.class, doa -> doa.selectUser(id));
    }

    @Override
    public User selectUser(String email){
        jdbi.registerRowMapper(new UserMapper());
        return jdbi.withExtension(UserDoa.class, doa -> doa.selectUser(email));
    }

    @Override
    public List<User> selectAllUser(){
        jdbi.registerRowMapper(new UserMapper());
        return jdbi.withExtension(UserDoa.class, doa -> doa.selectAllUser());
    }

    @Override
    public boolean deleteUser(int id){
        return jdbi.withExtension(UserDoa.class, doa -> doa.deleteUser(id));
    }

    public int getUniqueId(){
        List<Integer> results = jdbi.withHandle(handle -> {
            return  handle.createQuery("select * from nextval('usersequence')")
                    .mapTo(int.class)
                    .list();

        });

        return results.get(0).intValue();
    }
}
