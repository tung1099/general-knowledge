package com.example.user_api.service;

import com.example.user_api.model.Product;

public interface IProductService {
    Iterable<Product> getProducts();
    Product insertProduct(Product product);

}
