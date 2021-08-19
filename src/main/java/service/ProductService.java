package service;

import entity.Product;
import jdbc.JDBCProductDAO;

import java.util.List;

public class ProductService {
    public void setJdbcProductDAO(JDBCProductDAO jdbcProductDAO) {

        this.jdbcProductDAO = jdbcProductDAO;
    }

    private JDBCProductDAO jdbcProductDAO;

    public List<Product> getAll(){

        return jdbcProductDAO.getAllProducts();
    }

    public void update(Product product) {

        jdbcProductDAO.update(product);
    }

    public void delete(int id) {

        jdbcProductDAO.delete(id);
    }

    public void create(Product product) {

        jdbcProductDAO.create(product);
    }

    public Product findById(int id){

        return jdbcProductDAO.findById(id);
    }
}
