package servlet;

import entity.Product;
import generator.PageGenerator;
import service.ProductService;
import service.ServiceLocator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProductServlet extends HttpServlet {

    private ProductService service = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(req.getParameter("id"));

        Product product = service.findById(id);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product", product);

        String page = PageGenerator.generatePage(paramMap, "/edit-product.ftl");
        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price").replace(",", "."));
        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
        service.update(product);
        resp.sendRedirect("/products");
    }
}
