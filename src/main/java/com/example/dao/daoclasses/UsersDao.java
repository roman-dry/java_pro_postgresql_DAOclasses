package com.example.dao.daoclasses;

import com.example.dao.MyConnection;
import com.example.dao.models.User;

import java.sql.*;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDao {
    public void createUser(User user) {

        try(Connection cn = MyConnection.createNewConnection();
            Statement sm = cn.createStatement()) {
            sm.execute("insert into users values(DEFAULT, " +
                    "'" + user.getPhone() + "', '" + user.getName() + "', " +
                    "'" + user.getLastname() + "', '" + user.getGender() + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> readUsers() {
        List allUsers = new ArrayList<User>();
        try(Connection cn = MyConnection.createNewConnection();
            Statement sm = cn.createStatement()) {
            ResultSet rs = sm.executeQuery("select * from users");

            while(rs.next()) {
                int id = rs.getInt("id");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String gender = rs.getString("gender");
                allUsers.add(new User(id, phone, name, lastname, gender));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;

    }

    public User readUserById(int id) {
        User user = new User();
        try(Connection cn = MyConnection.createNewConnection();
            Statement sm = cn.createStatement()) {
            ResultSet rs = sm.executeQuery("select * from users where id = '" + id +"'");

            while(rs.next()) {
                user.setId(id);
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setGender(rs.getString("gender"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    public void updateUserById(User user, int id) {
        try(Connection cn = MyConnection.createNewConnection()) {
            PreparedStatement ps = cn.prepareStatement("update users " +
                    "set phone = '"+ user.getPhone() + "', " +
                    "name = '"+ user.getName() + "', " +
                    "lastname = '"+ user.getLastname() + "', " +
                    "gender = '"+ user.getGender() + "' where id = '" + id +"'");
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int id) {
        try(Connection cn = MyConnection.createNewConnection()) {
            PreparedStatement ps = cn.prepareStatement("delete from users " +
                    "where id = '" + id +"'");
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
