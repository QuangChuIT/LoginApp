package vn.aptech.logindb.service;

import vn.aptech.logindb.entity.Product;
import vn.aptech.logindb.util.DatabaseUtil;

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


    public List<Product> getProducts() {
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
}
