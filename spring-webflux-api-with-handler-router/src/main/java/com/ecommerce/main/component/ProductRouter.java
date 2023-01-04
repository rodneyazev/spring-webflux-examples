package com.ecommerce.main.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ProductRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(ProductHandler handler){
		return RouterFunctions
				.route(GET("/product").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/product/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(POST("/product").and(accept(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(DELETE("/product/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
	}
	
}
