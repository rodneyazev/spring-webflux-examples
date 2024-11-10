package com.ecommerce.main.service;

import com.ecommerce.main.document.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Flux<Product> findAll();
	Mono<Product> findById(String id);
	Mono<Product> save(Product product);
	Mono<Void> delete(String id);
	Mono<Product> update(Product product, String id);
}
