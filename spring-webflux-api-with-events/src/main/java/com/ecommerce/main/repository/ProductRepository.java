package com.ecommerce.main.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ecommerce.main.document.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {


}
