package com.ecommerce.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.main.document.Product;
import com.ecommerce.main.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Flux<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Mono<Product> findById(String id) {
		return productRepository.findById(id);
	}

	@Override
	public Mono<Product> save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Mono<Void> delete(String id) {
		return productRepository.deleteById(id);
	}

	@Override
	public Mono<Product> update(Product product, String id) {
		return productRepository.save(product);
	}

}
