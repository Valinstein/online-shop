package service;

import entity.Product;
import jdbc.impl.ProductDAO;

import java.util.List;

public class ProductService {
    public void setProductDAO(ProductDAO productDAO) {

        this.productDAO = productDAO;
    }

    private ProductDAO productDAO;

    public List<Product> getAll(){

        return productDAO.getAllProducts();
    }

    public void update(Product product) {

        productDAO.update(product);
    }

    public void delete(int id) {

        productDAO.delete(id);
    }

    public void create(Product product) {

        productDAO.create(product);
    }

    public Product findById(int id){

        return productDAO.findById(id);
    }
}
