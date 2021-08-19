package servlet;

import entity.Product;
import generator.PageGenerator;
import service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    public void setService(ProductService service) {
        this.service = service;
    }

    ProductService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> paramMap = new HashMap<>();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();
        resp.setContentType("text/html;charset=utf-8");
        List<Product> products = service.getAll();
        paramMap.put("products", products);
        resp.getWriter().write(PageGenerator.generatePage(paramMap, "/products.ftl"));
    }
}
