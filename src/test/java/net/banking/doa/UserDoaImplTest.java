package net.banking.doa;

import net.banking.doa.user.UserDoaImpl;
import net.banking.models.User;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDoaImplTest {

    private final UserDoaImpl userDoaImpl = new UserDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/bankingtest", "thaabit", "1234"));
    private final User userOne = new User(1, "Thaabit", "Jacobs", "thaabit@gmail.com", LocalDateTime.now());

    @Test
    void shouldReturnWhenUserAddedToDB(){
        userDoaImpl.clearAllUsers();
        assertTrue(userDoaImpl.insertUser(userOne));
    }

    @Test
    void shouldReturnTrueIfUpdateToDB(){
        userDoaImpl.clearAllUsers();
        userDoaImpl.insertUser(userOne);
        userOne.setFirstName("James");
        assertTrue(userDoaImpl.updateUser(userOne));
    }

    @Test
    void shouldReturnUserObjectWithMatchingId(){
        userDoaImpl.clearAllUsers();
        userDoaImpl.insertUser(userOne);
        User user = userDoaImpl.selectUser(1);
        assertEquals("thaabit@gmail.com", user.getEmail());
    }

    @Test
    void shouldReturnAListOfUsrs(){
        userDoaImpl.clearAllUsers();
        assertTrue(userDoaImpl.selectAllUser() instanceof List);
    }

    @Test
    void shouldReturnTrueWhenUserSuccesfullyDeleted(){
        userDoaImpl.clearAllUsers();
        userDoaImpl.insertUser(userOne);
        assertTrue(userDoaImpl.deleteUser(1));
    }
}
