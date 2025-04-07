package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        WebClient.Builder builder = mock(WebClient.Builder.class);
        webClient = mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec<?> requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        // Produto fake
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");

        ProductResponse response = new ProductResponse();
        response.setProducts(List.of(product));

        // Mocks do builder
        when(builder.baseUrl(anyString())).thenReturn(builder);
        when(builder.build()).thenReturn(webClient);

        // Mocks da cadeia WebClient
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(eq("/products"))).thenReturn(requestHeadersSpec);
        when(uriSpec.uri(eq("/products/{id}"), eq(1L))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        when(responseSpec.bodyToMono(eq(ProductResponse.class))).thenReturn(Mono.just(response));
        when(responseSpec.bodyToMono(eq(Product.class))).thenReturn(Mono.just(product));

        productService = new ProductService(builder, "https://dummyjson.com");
    }


    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts().collectList().block();

        assertEquals(1, products.size());
        assertEquals("Produto Teste", products.get(0).getTitle());
    }

    @Test
    void testGetProductById() {
        Product product = productService.getProductById(1L).block();

        assertEquals("Produto Teste", product.getTitle());
        assertEquals(1L, product.getId());
    }
}
