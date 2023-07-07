package br.com.compassuol.pb.challenge.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {
	
	@Bean
	public RouteLocator gatewayRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("products", p -> p
						.path("/products")
						.uri("http://localhost:8082"))
						.build();
				
				
	}
}
