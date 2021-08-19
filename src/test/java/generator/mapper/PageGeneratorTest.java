package generator.mapper;

import entity.Product;
import generator.PageGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PageGeneratorTest {

    @DisplayName("Get list of products")
    @Test
    void returnListOfProducts(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id(1).creationDate(timestamp).name("Lamp").price(200.00).build());
        products.add(Product.builder().id(2).creationDate(timestamp).name("Skate").price(500.00).build());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("products", products);
        String response = PageGenerator.generatePage(paramMap, "/products.ftl");
        assertNotNull(response);
    }

}