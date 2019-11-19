package se.lexicon.amin.springboot_jpa_assignment.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;
import se.lexicon.amin.springboot_jpa_assignment.entity.ProductOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductOrderRepositoryImplTest {

    @Autowired
    ProductOrderRepositoryImpl repository;

    @Test
    public void findById() {
        Optional<ProductOrder> result = repository.findById(101);

        assertEquals(101, result.get().getId());
        assertEquals(101, result.get().getCustomer().getId());
        assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0, 0), result.get().getOrderDateTime());
    }

    @Test
    public void findByDate() {
        List<ProductOrder> result = repository.findByDate(LocalDateTime.of(2019, 1, 1, 0, 0, 0));

        assertEquals(2, result.size());
        assertTrue(result.get(0).getId() == 101 || result.get(0).getId() == 105);
        assertTrue(result.get(1).getId() == 101 || result.get(1).getId() == 105);
    }

    @Test
    public void findByAppUserId() {
        List<ProductOrder> result = repository.findByAppUserId(102);


        assertEquals(1, result.size());
        assertEquals(103, result.get(0).getId());
    }

    @Test
    public void findByProductId() {
        List<ProductOrder> result = repository.findByProductId(103);


        assertEquals(2, result.size());
        assertTrue(result.get(0).getId() == 102 || result.get(0).getId() == 103);
        assertTrue(result.get(1).getId() == 102 || result.get(1).getId() == 103);
    }

    @Test
    public void findByProductName() {
        List<ProductOrder> result = repository.findByProductName("Saft");


        assertEquals(1, result.size());
        assertEquals(103, result.get(0).getId());

    }

    @Test
    public void findAll() {
        List<ProductOrder> result = repository.findAll();

        assertEquals(5, result.size());
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

        Optional<ProductOrder> result = repository.findById(101);
        assertEquals(LocalDateTime.of(2019, 1, 1, 0, 0, 0), result.get().getOrderDateTime());


        result.get().setOrderDateTime(LocalDateTime.of(2019,2,1,0,0,0));
        repository.save(result.get());

        Optional<ProductOrder> result2 = repository.findById(101);
        assertEquals(LocalDateTime.of(2019,2,1,0,0,0), result2.get().getOrderDateTime());

    }

    @Test
    @DirtiesContext
    public void save_ifNullTrowsException() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }
}