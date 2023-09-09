package vn.aptech.logindb.service;

import vn.aptech.logindb.entity.User;
import vn.aptech.logindb.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public User login(String username, String password) {
        Connection connection = DatabaseUtil.getConnection();
        try {
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                user.setId(id);
                user.setName(name);
                user.setUsername(username);
            }
            rs.close();
            statement.close();
            return user;
        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.login("admin", "123456999");
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Wrong username or password");
        }
    }
}
