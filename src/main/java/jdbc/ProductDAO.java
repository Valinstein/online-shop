package jdbc;

import entity.Product;

import java.util.List;

public interface ProductDAO {


    public List<Product> getAllProducts();

    public void create(Product product);


    public void update(Product product);

    public void delete(int id);

    public Product findById(int id);
}
