//package com.example.user_api.service;
//
//import com.example.common.config.elasticsearch.ElasticsearchAsync;
//import com.example.user_api.model.Product;
//import com.example.user_api.repository.IProductRepository;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class ProductServiceImpl implements IProductService{
//    @Autowired
//    private IProductRepository productRepository;
//
//    @Autowired
//    RestHighLevelClient restHighLevelClient;
//
//    @Override
//    public Iterable<Product> getProducts() {
//        return productRepository.findAll();
//    }
//
//    public Product insertProduct(Product product) {
//        return productRepository.save(product);
//    }
//}
