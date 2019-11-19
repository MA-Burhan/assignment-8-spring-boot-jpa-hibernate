package se.lexicon.amin.springboot_jpa_assignment.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {


    Product product;

    @BeforeEach
    public void setup(){
        product = new Product("Coca-Cola", 15.9);
    }

    @Test
    public void create_product(){

        assertEquals(0, product.getId());
        assertEquals("Coca-Cola", product.getName());
        assertEquals(15.9, product.getPrice());
        assertNull(product.getOrderItems());
    }

    @Test
    public void product_equals_hashcode(){
        Product product_copy = new Product();
        product_copy.setName("Coca-Cola");
        product_copy.setPrice(15.9);

        assertTrue(product.equals(product_copy));
        assertEquals(product.hashCode(), product_copy.hashCode());
    }

    @Test
    public void product_toString(){
        String toString = product.toString();
        assertTrue(toString.contains("id=0") &&
                toString.contains("name='Coca-Cola'") &&
                toString.contains("price=15.9"));
    }
}
