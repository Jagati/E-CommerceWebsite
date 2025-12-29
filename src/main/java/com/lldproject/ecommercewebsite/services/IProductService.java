package com.lldproject.ecommercewebsite.services;

import com.lldproject.ecommercewebsite.models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
}
