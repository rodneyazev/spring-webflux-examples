package com.ecommerce.main.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.main.document.Product;
import com.ecommerce.main.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	 
	@GetMapping(value="/product")
	public Flux<Product> getProduct(){
		return productService.findAll();                                                                                     
	}
	
	@GetMapping(value="/product/{id}")
	public Mono<Product> getProductId(@PathVariable String id){
		return productService.findById(id);
	}
	
	@PostMapping(value="/product")
	public Mono<Product> saveProduct(@RequestBody Product product){
		return productService.save(product);
	}
	
	@DeleteMapping(value="/product/{id}")
	public Mono<Void> deleteProduct(@PathVariable String id){
		return productService.delete(id);
	}
	
	@PutMapping(value="/product/{id}")
	public Mono<Product> updateProduct(@RequestBody Product product, @PathVariable String id) {
		product.setId(id);
		return productService.save(product);
	}
	
	@GetMapping(value="/product/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Product>> getProductByWebflux(){

		System.out.println("---Start get Products by WEBFLUX--- " + LocalDateTime.now());
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Product> productFlux = productService.findAll();

        return Flux.zip(interval, productFlux);
        
	}

}
