package vn.aptech.logindb.service;

import vn.aptech.logindb.entity.Product;
import vn.aptech.logindb.util.DatabaseUtil;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService INSTANCE;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductService();
        }
        return INSTANCE;
    }


    public List<Product> getProductsUsingJDBC() {
        Connection connection = DatabaseUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from products");
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Integer quantity = rs.getInt("quantity");
                Long price = rs.getLong("price");
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                products.add(product);
            }
            rs.close();
            statement.close();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            DatabaseUtil.close(connection);
        }
    }


    public List<Product> getProductsUsingJPA(String name) {
        EntityManager em = Persistence.createEntityManagerFactory("loginapp-pu").createEntityManager();
        try {
            Query query = em.createQuery("select p from Products p where :name is null or name like :name", Product.class);
            if (name == null || name.isEmpty()) {
                query.setParameter("name", name);
            } else {
                query.setParameter("name", "%" + name + "%");
            }

            return (List<Product>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
