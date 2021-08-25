package web.controller;

import entity.Product;
import generator.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    @ResponseBody
    public String getAll() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("products", service.getAll());
        return PageGenerator.generatePage(paramMap, "/products.ftl");
    }

    @GetMapping("/new")
    protected String newProductPage(HttpServletResponse resp) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();

        return PageGenerator.generatePage(paramMap, "new-product.ftl");
    }

    @PostMapping("/new")
    protected void saveNewProduct(@RequestParam String price,
                                  @RequestParam String name,
                                  HttpServletResponse resp) throws IOException {
        double newPrice = Double.parseDouble(price.replace(",", "."));

        Product product = Product.builder()
                .name(name)
                .price(newPrice)
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .build();

        service.create(product);
        resp.sendRedirect("/products");
    }

    @PostMapping("/delete")
    protected void deleteProduct(@RequestParam int id, HttpServletResponse resp) throws IOException {
        service.delete(id);
        resp.sendRedirect("/products");
    }

    @GetMapping("/edit")
    protected String editProduct(@RequestParam int id, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        Product product = service.findById(id);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product", product);

        return PageGenerator.generatePage(paramMap, "/edit-product.ftl");
    }

    @PostMapping("/edit")
    protected void saveEditedProduct(@RequestParam int id,
                                     @RequestParam String name,
                                     @RequestParam String price,
                                     HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        double newPrice = Double.parseDouble(price.replace(",", "."));
        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(newPrice)
                .build();
        service.update(product);
        resp.sendRedirect("/products");
    }
}
