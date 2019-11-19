package se.lexicon.amin.springboot_jpa_assignment.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.amin.springboot_jpa_assignment.entity.OrderItem;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderItemRepositoryImplTest {

    @Autowired
    OrderItemRepositoryImpl repository;

    @Test
    public void findById() {
        Optional<OrderItem> result = repository.findById(101);
        assertEquals(2, result.get().getQuantity());
        assertEquals(101, result.get().getProductOrder().getId());
        assertEquals(101,result.get().getProduct().getId());

    }

    @Test
    public void findAll() {
        List<OrderItem> result = repository.findAll();

        assertEquals(8, result.size());
    }

    @Test
    @DirtiesContext
    public void deleteById() {
        repository.delete(101);

        assertFalse(repository.findById(101).isPresent());
    }

    @Test
    @DirtiesContext
    public void save() {

        Optional<OrderItem> result = repository.findById(101);
        assertEquals(2, result.get().getQuantity());

        result.get().setQuantity(4);
        repository.save(result.get());

        Optional<OrderItem> result2 = repository.findById(101);
        assertEquals(4, result2.get().getQuantity());
    }

    @Test
    @DirtiesContext
    public void save_ifNullTrowsException() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }
}
