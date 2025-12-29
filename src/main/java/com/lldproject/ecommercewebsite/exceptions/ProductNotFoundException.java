package com.lldproject.ecommercewebsite.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
