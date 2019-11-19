package se.lexicon.amin.springboot_jpa_assignment.data;

import org.springframework.stereotype.Repository;
import se.lexicon.amin.springboot_jpa_assignment.entity.AppUser;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AppUserRepositoryImpl implements AppUserRepository {


    @PersistenceContext
    EntityManager em;



    @Override
    public AppUser save(AppUser appUser) {
        if (appUser.getId() == 0) {
            em.persist(appUser);
        } else {
            em.merge(appUser);
        }

        return appUser;
    }


    @Override
    public Optional<AppUser> findById(int id) {

        return Optional.ofNullable(em.find(AppUser.class, id));
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {


        TypedQuery<AppUser> query = em.createQuery("SELECT a FROM AppUser a WHERE a.email= ?1", AppUser.class);
        query.setParameter(1, email);

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<AppUser> findByFirstName(String firstName) {
        TypedQuery<AppUser> query = em.createQuery("SELECT a FROM AppUser a WHERE a.firstName= ?1", AppUser.class);
        query.setParameter(1, firstName);
        return query.getResultList();
    }

    @Override
    public List<AppUser> findByLastName(String lastName) {
        TypedQuery<AppUser> query = em.createQuery("SELECT a FROM AppUser a WHERE a.lastName= ?1", AppUser.class);
        query.setParameter(1, lastName);
        return query.getResultList();
    }


    @Override
    public List<AppUser> findAll() {
        return em.createQuery("Select a from AppUser a", AppUser.class).getResultList();
    }


    @Override
    public void delete(int id){
        Optional<AppUser> result = findById(id);

        if(result.isPresent()) {
            em.remove(result.get());
        }

    }
}
