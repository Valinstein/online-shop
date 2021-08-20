package service;

import configLoader.ConfigLoader;
import jdbc.JDBCProductDAO;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        ConfigLoader configLoader = new ConfigLoader("application.properties");
        JDBCProductDAO productDAO = new JDBCProductDAO();
        ProductService service = new ProductService();
        productDAO.setConfigLoader(configLoader);
        service.setJdbcProductDAO(productDAO);

        addService(ProductService.class, service);
    }

    public static <T> T getService(Class<T> serviceType){
        return serviceType.cast(SERVICES.get(serviceType));
    }

    public static void addService(Class<?> serviceName, Object service) {
        SERVICES.put(serviceName, service);
    }
}
