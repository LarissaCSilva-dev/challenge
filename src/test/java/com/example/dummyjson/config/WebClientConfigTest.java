package com.example.dummyjson.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Carrega o contexto completo
@Import(WebClientConfig.class) // Importa sua configuração
public class WebClientConfigTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void testWebClientBeanCreation() {
        WebClient webClient = webClientBuilder.baseUrl("http://localhost:" + 8080).build();
        assertNotNull(webClient, "WebClient bean should not be null");
    }
}
