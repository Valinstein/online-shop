package service;

import entity.Product;
import jdbc.JDBCProductDAO;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private JDBCProductDAO productDAO;
    private Product product;
    private ProductService service;

    @BeforeEach
    void init() {
        service = new ProductService();
        productDAO = mock(JDBCProductDAO.class);
        product = new Product();
        service.setJdbcProductDAO(productDAO);
    }


    @Test
    void getAll() {

        when(productDAO.getAllProducts()).thenReturn(List.of(product));

        List<Product> products = service.getAll();
        assertNotNull(products);
        verify(productDAO, times(1)).getAllProducts();
    }

    @Test
    void update() {
        productDAO.update(product);
        verify(productDAO, times(1)).update(product);
    }

    @Test
    void delete() {
        productDAO.delete(product.getId());
        verify(productDAO, times(1)).delete(anyInt());
    }

    @Test
    void create() {
        productDAO.create(product);
        verify(productDAO, times(1)).create(product);
    }

    @Test
    void findById() {
        when(productDAO.findById(anyInt())).thenReturn(product);
        Product testProduct = productDAO.findById(product.getId());
        assertEquals(product, testProduct);
        verify(productDAO, times(1)).findById(anyInt());
    }
}