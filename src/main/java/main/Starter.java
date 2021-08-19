package main;

import configLoader.ConfigLoader;
import jdbc.JDBCProductDAO;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.ProductService;
import servlet.DeleteProductServlet;
import servlet.EditProductServlet;
import servlet.NewProductServlet;
import servlet.ProductsServlet;

public class Starter {
    public static void main(String[] args) throws Exception {

        JDBCProductDAO productDAO = new JDBCProductDAO();
        ProductService service = new ProductService();
        NewProductServlet newProductServlet = new NewProductServlet();
        ProductsServlet allProdacts = new ProductsServlet();
        EditProductServlet editProductServlet = new EditProductServlet();
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet();
        ConfigLoader configLoader = new ConfigLoader("application.properties");

        productDAO.setConfigLoader(configLoader);
        service.setJdbcProductDAO(productDAO);
        newProductServlet.setService(service);
        allProdacts.setService(service);
        editProductServlet.setService(service);
        deleteProductServlet.setService(service);
        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHolder newProductHolder = new ServletHolder(newProductServlet);
        ServletHolder allProductsHolder = new ServletHolder(allProdacts);
        ServletHolder editProductHolder = new ServletHolder(editProductServlet);
        ServletHolder deleteProductHolder = new ServletHolder(deleteProductServlet);

        contextHandler.addServlet(allProductsHolder, "/");
        contextHandler.addServlet(allProductsHolder, "/products");
        contextHandler.addServlet(newProductHolder, "/product/new");
        contextHandler.addServlet(editProductHolder, "/product/edit");
        contextHandler.addServlet(deleteProductHolder, "/product/delete");

        server.setHandler(contextHandler);
        server.start();

    }
}
