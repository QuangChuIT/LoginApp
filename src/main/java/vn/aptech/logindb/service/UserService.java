package vn.aptech.logindb.service;

import vn.aptech.logindb.entity.User;
import vn.aptech.logindb.util.DatabaseUtil;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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

    public User loginUsingJPA(String username, String password) {
        EntityManager em = Persistence.createEntityManagerFactory("loginapp-pu").createEntityManager();
        User user = null;
        try {
            Query query = em.createQuery("select u from Users u where u.username = :username and u.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                user = (User) list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return user;
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
