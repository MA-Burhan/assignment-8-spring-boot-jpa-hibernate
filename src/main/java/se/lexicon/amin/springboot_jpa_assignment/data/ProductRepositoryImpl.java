package se.lexicon.amin.springboot_jpa_assignment.data;

import org.springframework.stereotype.Repository;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    EntityManager em;


    @Override
    public Product save(Product product) {
        if (product.getId() == 0) {
            em.persist(product);
        } else {
            em.merge(product);
        }

        return product;
    }


    @Override
    public Optional<Product> findById(int id) {

        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public List<Product> findByName(String name) {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.name= ?1", Product.class);
        query.setParameter(1, name);
        return query.getResultList();
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("Select p from Product p", Product.class).getResultList();
    }


    @Override
    public void delete(int id){
        Optional<Product> result = findById(id);
        if (result.isPresent()) {

            em.remove(result.get());
        }

    }
}
