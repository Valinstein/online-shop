package web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.BucketService;

public class BucketController {

    private BucketService bucketService;

    @PostMapping("/add")
    public String addToBucket(@CookieValue("user-token") String token,
                              @RequestParam Integer productId) {
        bucketService.addProductToBucket(token, productId);
        return "products";
    }

    @PostMapping("/remove")
    public String removeFromBucket(@CookieValue("user-token") String token,
                                   @RequestParam Integer productId) {
        bucketService.removeProductFromBucket(token, productId);
        return "bucket";
    }

    @GetMapping
    public String getBucketPage(@CookieValue("user-token") String token,
                                Model model){
        model.addAttribute("products", bucketService.getUserBucket(token));
        return "bucket";
    }
}
