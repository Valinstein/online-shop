package web.controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;

import java.sql.Timestamp;

@Controller
public class ProductController {


    @Autowired
    ProductService service;

    @GetMapping("/products")
    public String getAll(Model model) {
        model.addAttribute("products", service.getAll());
        return "products";
    }

    @GetMapping("/product/new")
    protected String newProductPage() {

        return "new-product";
    }

    @PostMapping("/product/new")
    protected String saveNewProduct(@RequestParam String price,
                                    @RequestParam String name) {
        double newPrice = Double.parseDouble(price.replace(",", "."));

        Product product = Product.builder()
                .name(name)
                .price(newPrice)
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .build();

        service.create(product);
        return "redirect:/products";
    }

    @PostMapping("/product/delete")
    protected String deleteProduct(@RequestParam int id) {
        service.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/product/edit")
    protected String editProduct(@RequestParam int id, Model model) {

        Product product = service.findById(id);

        model.addAttribute("product", product);

        return "edit-product";
    }

    @PostMapping("/product/edit")
    protected String saveEditedProduct(@RequestParam int id,
                                       @RequestParam String name,
                                       @RequestParam String price) {
        double newPrice = Double.parseDouble(price.replace(",", "."));
        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(newPrice)
                .build();
        service.update(product);
        return "redirect:/products";
    }
}
