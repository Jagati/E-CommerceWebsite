package com.lldproject.ecommercewebsite.controllers;

import com.lldproject.ecommercewebsite.dtos.CategoryDto;
import com.lldproject.ecommercewebsite.dtos.ProductDto;
import com.lldproject.ecommercewebsite.exceptions.ProductNotFoundException;
import com.lldproject.ecommercewebsite.models.Product;
import com.lldproject.ecommercewebsite.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
       List<Product> products = productService.getAllProducts();
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        if(id<1){
            //throw new IllegalArgumentException("ID should be greater than 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productService.getProductById(id);
        if(product==null){
            //throw new ProductNotFoundException("Product not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    private ProductDto from(Product  product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

}
