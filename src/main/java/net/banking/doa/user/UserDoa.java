package net.banking.doa.user;

import net.banking.models.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface UserDoa {

    @SqlUpdate("insert into users (id, first_name, last_name, email, date_created) " +
            "values (:id, :firstName, :lastName, :email, :dateCreated)")
    boolean insertUser(@BindBean User user);

    @SqlQuery("select * from users where id=:id")
    User selectUser(@Bind("id") int id);

    @SqlQuery("select * from users")
    List<User> selectAllUser();

    @SqlUpdate("update users set first_name=:firstName, last_name=:lastName, email=:email where id=:id")
    boolean updateUser(@BindBean User user);

    @SqlUpdate("delete from users")
    boolean clearAllUsers();

    @SqlUpdate("delete from users where id=:id")
    boolean deleteUser(@Bind("id") int id);
}
