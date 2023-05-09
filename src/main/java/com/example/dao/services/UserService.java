package com.example.dao.services;

import com.example.dao.daoclasses.UsersDao;
import com.example.dao.models.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class UserService {
    private final UsersDao userdao;

    public void createUser(User user) {
        userdao.createUser(user);
    }
    public List<User> readUsers() {
        return userdao.readUsers();
    }
    public User readUserById(int id) {
        return userdao.readUserById(id);
    }
    public void updateUserById(User user, int id) {
        userdao.updateUserById(user, id);
    }

    public void deleteUserById(int id) {
        userdao.deleteUserById(id);
    }


}
