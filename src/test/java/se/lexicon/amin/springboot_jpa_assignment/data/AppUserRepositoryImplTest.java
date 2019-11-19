package se.lexicon.amin.springboot_jpa_assignment.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import se.lexicon.amin.springboot_jpa_assignment.entity.AppUser;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AppUserRepositoryImplTest {

    @Autowired
    AppUserRepositoryImpl repository;

    @Test
    public void findById() {
        Optional<AppUser> appUser = repository.findById(101);
        assertEquals("Patrik", appUser.get().getFirstName());
        assertEquals("Svensson", appUser.get().getLastName());
        assertEquals("patrik_svensson@email.com", appUser.get().getEmail());

    }

    @Test
    public void findByEmail() {
        Optional<AppUser> appUser = repository.findByEmail("patrik_svensson@email.com");
        assertEquals(101, appUser.get().getId());
        assertEquals("Patrik", appUser.get().getFirstName());
        assertEquals("Svensson", appUser.get().getLastName());


    }

    @Test
    public void findByFirstName() {
        List<AppUser> result = repository.findByFirstName("Erik");

        assertEquals(2, result.size());
        assertTrue(result.get(0).getId() == 103 || result.get(0).getId() == 105);
        assertTrue(result.get(1).getId() == 103 || result.get(1).getId() == 105);

    }

    @Test
    public void findByLastName() {
        List<AppUser> result = repository.findByLastName("Svensson");

        assertEquals(2, result.size());
        assertTrue(result.get(0).getId() == 101 || result.get(0).getId() == 104);
        assertTrue(result.get(1).getId() == 101 || result.get(1).getId() == 104);

    }

    @Test
    public void findAll() {
        List<AppUser> result = repository.findAll();

        assertEquals(6, result.size());
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

        Optional<AppUser> result = repository.findById(101);
        assertEquals("patrik_svensson@email.com", result.get().getEmail());

        result.get().setEmail("svensson_patrik@email.com");
        repository.save(result.get());

        Optional<AppUser> result2 = repository.findById(101);
        assertEquals("svensson_patrik@email.com", result2.get().getEmail());


    }
}
