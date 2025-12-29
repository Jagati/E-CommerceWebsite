package com.lldproject.ecommercewebsite.services;

import com.lldproject.ecommercewebsite.dtos.FakeStoreProductDto;
import com.lldproject.ecommercewebsite.dtos.ProductDto;
import com.lldproject.ecommercewebsite.models.Category;
import com.lldproject.ecommercewebsite.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements IProductService{
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Override
    public Product getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);
        if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().
                        equals(HttpStatusCode.valueOf(200))){
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }
    @Override
    public List<Product> getAllProducts(){
        return null;
    }
    @Override
    public Product createProduct(Product product){
        return null;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
