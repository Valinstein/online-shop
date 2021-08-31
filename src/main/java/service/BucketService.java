package service;

import entity.Product;
import service.security.LoginService;

import java.util.List;

public class BucketService {

    private LoginService loginService;
    private ProductService productService;

    public void addProductToBucket(String token, int productId){
        List<Product> userBucket = loginService.getSessions().get(token).getBucket();
        userBucket.add(productService.findById(productId));
    }

    public void removeProductFromBucket(String token, int productId){
        List<Product> userBucket = loginService.getSessions().get(token).getBucket();
        userBucket.remove(productService.findById(productId));
    }

    public List<Product> getUserBucket (String token){
        return loginService.getSessions().get(token).getBucket();
    }
}
