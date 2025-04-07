package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(WebClient.Builder webClientBuilder, @Value("${dummy.api.base-url}") String apiUrl) {
        this.webClient = webClientBuilder
                .baseUrl(apiUrl)
                .build();
    }

    public Flux<Product> getAllProducts() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.getProducts()));
    }

    public Mono<Product> getProductById(Long id) {
        return webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }
}