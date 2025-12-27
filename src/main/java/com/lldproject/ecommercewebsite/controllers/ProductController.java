package com.lldproject.ecommercewebsite.controllers;

import com.lldproject.ecommercewebsite.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Product 1");
        product.setName("Product 1");
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = new Product();
        product.setId(id);
        product.setDescription("Product iphone");
        product.setName("IPhone");
        return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

}
