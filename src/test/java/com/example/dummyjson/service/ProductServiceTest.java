package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;
    private WebClient.Builder webClientBuilder;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        WebClient webClient = mock(WebClient.class);
        webClientBuilder = mock(WebClient.Builder.class);

        // mocks da cadeia fluente do WebClient
        RequestHeadersUriSpec<?> uriSpecMock = mock(RequestHeadersUriSpec.class);
        RequestHeadersSpec<?> headersSpecMock = mock(RequestHeadersSpec.class);
        ResponseSpec responseSpecMock = mock(ResponseSpec.class);

        // objeto de retorno simulado
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Produto Teste");

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(List.of(product));

        // encadeamento dos mocks
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);

        when(webClient.get()).thenReturn((RequestHeadersUriSpec) uriSpecMock);
        when(uriSpecMock.uri("/products")).thenReturn((RequestHeadersSpec) headersSpecMock);
        when(headersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ProductResponse.class)).thenReturn(Mono.just(productResponse));

        productService = new ProductService(webClientBuilder, "https://dummyjson.com");
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();

        assertEquals(1, products.size());
        assertEquals("Produto Teste", products.get(0).getTitle());
        assertEquals(1L, products.get(0).getId());
    }
}
