package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest; // Usando WebFluxTest
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;  // Usando WebTestClient para WebFlux

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        // Mockando um produto de exemplo
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");
        product.setDescription("Descrição teste");
        product.setPrice(100.0);

        // Configurando o mock do ProductService
        when(productService.getAllProducts()).thenReturn(List.of(product));

        // Realizando a requisição e verificando a resposta
        webTestClient.get().uri("/api/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()  // Verifica se o status é OK (200)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)  // Verifica o tipo de conteúdo
                .expectBody()  // Verifica o corpo da resposta
                .jsonPath("$[0].id").isEqualTo(1)  // Verifica o id do produto
                .jsonPath("$[0].title").isEqualTo("Produto Teste")  // Verifica o título do produto
                .jsonPath("$[0].description").isEqualTo("Descrição teste")  // Verifica a descrição
                .jsonPath("$[0].price").isEqualTo(100.0);  // Verifica o preço
    }

    @Test
    void testGetProductById() {
        // Mockando um produto de exemplo
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");
        product.setDescription("Descrição teste");
        product.setPrice(100.0);

        // Configurando o mock do ProductService
        when(productService.getProductById(1L)).thenReturn(product);

        // Realizando a requisição e verificando a resposta
        webTestClient.get().uri("/api/products/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()  // Verifica se o status é OK (200)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)  // Verifica o tipo de conteúdo
                .expectBody()  // Verifica o corpo da resposta
                .jsonPath("$.id").isEqualTo(1)  // Verifica o id do produto
                .jsonPath("$.title").isEqualTo("Produto Teste")  // Verifica o título do produto
                .jsonPath("$.description").isEqualTo("Descrição teste")  // Verifica a descrição
                .jsonPath("$.price").isEqualTo(100.0);  // Verifica o preço
    }
}
