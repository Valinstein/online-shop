package jdbc.impl;

import entity.Product;
import jdbc.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

public class ProductDAO implements jdbc.ProductDAO {
    private static final String GET_ALL_PRODUCTS = "SELECT id, product_name, product_price, creation_date FROM Products";
    private static final String ADD_NEW_PRODUCT_QUERY = "INSERT INTO Products(product_name, product_price, creation_date) "
            + "VALUES (?, ?, ?);";
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE products SET product_name = ?, product_price = ? "
            + "WHERE id = ?;";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE id = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT id, product_name, product_price, creation_date FROM products "
            + "WHERE id = ?";

    @Autowired
    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Product> getAllProducts() {
        return jdbcTemplate.query(GET_ALL_PRODUCTS, new ProductRowMapper());
    }

    public void create(Product product) {
        jdbcTemplate.update(ADD_NEW_PRODUCT_QUERY, product.getName(), product.getPrice(),
                new Timestamp(System.currentTimeMillis()));
    }

    public void update(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT_QUERY, product.getName(), product.getPrice(), product.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_PRODUCT_QUERY, id);
    }

    public Product findById(int id) {
        return jdbcTemplate.query(FIND_BY_ID_QUERY, new ProductRowMapper(), id).get(0);
    }
}
