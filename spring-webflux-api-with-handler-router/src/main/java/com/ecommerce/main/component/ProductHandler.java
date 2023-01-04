package com.ecommerce.main.component;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecommerce.main.document.Product;
import com.ecommerce.main.service.ProductService;

import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

	@Autowired
	ProductService productService;
	
	public Mono<ServerResponse> findAll(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productService.findAll(), Product.class);
	}
	
	public Mono<ServerResponse> findById(ServerRequest request){
		String id = request.pathVariable("id");
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productService.findById(id), Product.class);
	}
	
	public Mono<ServerResponse> save(ServerRequest request){
		final Mono<Product> product = request.bodyToMono(Product.class);
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(product.flatMap(productService::save), Product.class));
	}
	
	public Mono<ServerResponse> delete(ServerRequest request){
		String id = request.pathVariable("id");
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productService.delete(id), Product.class);
	}
}
