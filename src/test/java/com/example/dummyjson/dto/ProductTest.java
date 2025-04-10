package com.example.dummyjson.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testGetAndSetter() {
        Long expectedId = 1L;
        String expectedTitle = "A dummy title";
        String expectedDescription = "A dummy description";
        Double expectedPrice = 2.1;

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("A dummy title");
        product1.setDescription("A dummy description");
        product1.setPrice(2.1);

        assertEquals(expectedId, product1.getId());
        assertEquals(expectedTitle, product1.getTitle());
        assertEquals(expectedDescription, product1.getDescription());
        assertEquals(expectedPrice, product1.getPrice());
    }
}
