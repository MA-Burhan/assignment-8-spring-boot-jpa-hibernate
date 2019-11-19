package se.lexicon.amin.springboot_jpa_assignment.data;

import org.springframework.stereotype.Repository;
import se.lexicon.amin.springboot_jpa_assignment.entity.AppUser;
import se.lexicon.amin.springboot_jpa_assignment.entity.OrderItem;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;
import se.lexicon.amin.springboot_jpa_assignment.entity.ProductOrder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductOrderRepositoryImpl {

    @PersistenceContext
    EntityManager em;


    public ProductOrder save(ProductOrder productOrder) {
        if (productOrder.getId() == 0) {
            em.persist(productOrder);
        } else {
            em.merge(productOrder);
        }

        return productOrder;
    }


    public Optional<ProductOrder> findById(int id) {

        return Optional.ofNullable(em.find(ProductOrder.class, id));
    }

    public List<ProductOrder> findByDate(LocalDateTime orderDate) {
        TypedQuery<ProductOrder> query = em.createQuery("SELECT p FROM ProductOrder p WHERE p.orderDateTime= ?1",
                                        ProductOrder.class);
        query.setParameter(1, orderDate);
        return query.getResultList();
    }

    public List<ProductOrder> findByAppUserId(int appUserId) {

        TypedQuery<ProductOrder> query = em.createQuery("SELECT p FROM ProductOrder p WHERE p.customer.id= ?1", ProductOrder.class);
        query.setParameter(1, appUserId);
        return query.getResultList();
    }

    public List<ProductOrder> findByProductId(int productId) {


        TypedQuery<ProductOrder> query = em.createQuery(
                "SELECT DISTINCT po FROM ProductOrder po JOIN po.orderItems o JOIN o.product p " +
                        "WHERE p.id= ?1", ProductOrder.class);
        query.setParameter(1, productId);
        return query.getResultList();

    }

    public List<ProductOrder> findByProductName(String productName) {


        TypedQuery<ProductOrder> query = em.createQuery(
                "SELECT DISTINCT po FROM ProductOrder po JOIN po.orderItems o JOIN o.product p " +
                        "WHERE p.name= ?1", ProductOrder.class);
        query.setParameter(1, productName);
        return query.getResultList();
    }


    public List<ProductOrder> findAll() {
        return em.createQuery("Select p from ProductOrder p", ProductOrder.class).getResultList();
    }

        public void delete(int id){
        Optional<ProductOrder> result = findById(id);

        if(result.isPresent()) {
            em.remove(result.get());
        }

    }
}
