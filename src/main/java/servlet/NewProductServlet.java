package servlet;

import entity.Product;
import generator.PageGenerator;
import service.ProductService;
import service.ServiceLocator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class NewProductServlet extends HttpServlet {


    private ProductService service = ServiceLocator.getService(ProductService.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();

        String page = PageGenerator.generatePage(paramMap, "new-product.ftl");
        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price").replace(",", "."));

        Product product = Product.builder()
                .name(name)
                .price(price)
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .build();

        service.create(product);
        resp.sendRedirect("/products");
    }
}
