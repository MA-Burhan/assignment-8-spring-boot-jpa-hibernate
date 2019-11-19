package se.lexicon.amin.springboot_jpa_assignment.data;

import se.lexicon.amin.springboot_jpa_assignment.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository {
    AppUser save(AppUser appUser);

    Optional<AppUser> findById(int id);

    Optional<AppUser> findByEmail(String email);

    List<AppUser> findByFirstName(String firstName);

    List<AppUser> findByLastName(String lastName);

    List<AppUser> findAll();

    void delete(int id);
}
