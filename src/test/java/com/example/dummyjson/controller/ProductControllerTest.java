package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");
        product.setDescription("Descrição teste");
        product.setPrice(100.0);

        // Corrigido: retorna um Flux
        when(productService.getAllProducts()).thenReturn(Flux.just(product));

        webTestClient.get().uri("/api/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].title").isEqualTo("Produto Teste")
                .jsonPath("$[0].description").isEqualTo("Descrição teste")
                .jsonPath("$[0].price").isEqualTo(100.0);
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");
        product.setDescription("Descrição teste");
        product.setPrice(100.0);

        // Corrigido: retorna um Mono
        when(productService.getProductById(1L)).thenReturn(Mono.just(product));

        webTestClient.get().uri("/api/products/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.title").isEqualTo("Produto Teste")
                .jsonPath("$.description").isEqualTo("Descrição teste")
                .jsonPath("$.price").isEqualTo(100.0);
    }
}
