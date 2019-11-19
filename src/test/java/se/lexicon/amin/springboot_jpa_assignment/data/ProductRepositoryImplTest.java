package se.lexicon.amin.springboot_jpa_assignment.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepositoryImplTest {

    @Autowired
    ProductRepositoryImpl repository;

    @Test
    public void findById() {
        Optional<Product> product = repository.findById(101);

        assertEquals("fanta", product.get().getName());
        assertEquals(16.0, product.get().getPrice());
    }

    @Test
    public void findByName() {
        List<Product> productList = repository.findByName("Juice");

        assertEquals(1, productList.size());
        assertTrue(productList.get(0).getId() == 103 && productList.get(0).getPrice() == 17.9);

    }

    @Test
    public void findAll() {
        List<Product> productList = repository.findAll();

        System.out.println(productList);
        assertEquals(5, productList.size());
    }

    @Test
    @DirtiesContext
    void deleteById() {
        repository.delete(101);

        assertFalse(repository.findById(101).isPresent());

    }


    @Test
    @DirtiesContext
    void save() {

        Optional<Product> product = repository.findById(101);
        assertEquals("fanta", product.get().getName());
        assertEquals(16.0, product.get().getPrice());

        product.get().setName("fanta Exotic");
        repository.save(product.get());

        Optional<Product> product2 = repository.findById(101);
        assertEquals("fanta Exotic", product2.get().getName());

    }

    @Test
    @DirtiesContext
    public void save_ifNullTrowsException() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }
}
