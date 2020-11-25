package net.banking.doa.user;

import net.banking.mappers.UserMapper;
import net.banking.models.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class UserDoaImpl implements UserDoa{

    private  Jdbi  jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/banking", "thaabit", "1234");

    public UserDoaImpl(){
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
    }

    public UserDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new UserMapper());
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
