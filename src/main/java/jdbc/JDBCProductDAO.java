package jdbc;

import configLoader.ConfigLoader;
import entity.Product;
import jdbc.mapper.ProductRowMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductDAO {
    private static final String GET_ALL_QUERY = "SELECT id, product_name, product_price, creation_date FROM Products";
    private static final String ADD_NEW_PRODUCT_QUERY = "INSERT INTO Products(product_name, product_price, creation_date) "
            + "VALUES (?, ?, ?);";
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE products SET product_name = ?, product_price = ? "
            + "WHERE id = ?;";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, product_name, product_price, creation_date FROM products "
            + "WHERE id = ?";

    private ConfigLoader configLoader;

    public void setConfigLoader(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(configLoader.getUrl(), configLoader.getUser(), configLoader.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        try (Connection connection = getConnection();) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            ProductRowMapper mapper = new ProductRowMapper();
            while (resultSet.next()) {
                allProducts.add(mapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allProducts;
    }

    public void create(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     ADD_NEW_PRODUCT_QUERY)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     UPDATE_PRODUCT_QUERY)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setDouble(3, product.getId());
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     DELETE_PRODUCT_QUERY)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Product findById(int id) {
        ProductRowMapper mapper = new ProductRowMapper();
        Product product;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     FIND_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = mapper.mapRow(resultSet);
                return product;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return new Product();
    }
}
