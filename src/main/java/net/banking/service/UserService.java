package net.banking.service;

import net.banking.doa.user.UserDoaImpl;
import net.banking.models.Account;
import net.banking.models.User;

import java.util.List;

public class UserService {

    private final UserDoaImpl userDoaImpl = new UserDoaImpl();

    public boolean insertUser(User user){
       return userDoaImpl.insertUser(user);
    }

    public User selectUser(int id){
        return userDoaImpl.selectUser(id);
    }
    public User selectUser(String email){
        return userDoaImpl.selectUser(email);
    }
    public List<User> selectAllUser(){
        return userDoaImpl.selectAllUser();
    }

    public boolean updateUser(User user){
        return userDoaImpl.updateUser(user);
    }

    public boolean deleteUser(int id){
        return userDoaImpl.deleteUser(id);
    }

    public int getUniqueId(){
        return userDoaImpl.getUniqueId();
    }
}
