package se.lexicon.amin.springboot_jpa_assignment.data;

import org.springframework.stereotype.Repository;
import se.lexicon.amin.springboot_jpa_assignment.entity.ProductOrder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductOrderRepositoryImpl implements ProductOrderRepository {

    @PersistenceContext
    EntityManager em;


    @Override
    public ProductOrder save(ProductOrder productOrder) throws IllegalArgumentException {
        if(productOrder == null) {
            throw new IllegalArgumentException("Product Order is null");
        }
        if (productOrder.getId() == 0) {
            em.persist(productOrder);
        } else {
            em.merge(productOrder);
        }

        return productOrder;
    }


    @Override
    public Optional<ProductOrder> findById(int id) {

        return Optional.ofNullable(em.find(ProductOrder.class, id));
    }

    @Override
    public List<ProductOrder> findByDate(LocalDateTime orderDate) {
        TypedQuery<ProductOrder> query = em.createQuery("SELECT p FROM ProductOrder p WHERE p.orderDateTime= ?1",
                                        ProductOrder.class);
        query.setParameter(1, orderDate);
        return query.getResultList();
    }

    @Override
    public List<ProductOrder> findByAppUserId(int appUserId) {

        TypedQuery<ProductOrder> query = em.createQuery("SELECT p FROM ProductOrder p WHERE p.customer.id= ?1", ProductOrder.class);
        query.setParameter(1, appUserId);
        return query.getResultList();
    }

    @Override
    public List<ProductOrder> findByProductId(int productId) {


        TypedQuery<ProductOrder> query = em.createQuery(
                "SELECT DISTINCT po FROM ProductOrder po JOIN po.orderItems o JOIN o.product p " +
                        "WHERE p.id= ?1", ProductOrder.class);
        query.setParameter(1, productId);
        return query.getResultList();

    }

    @Override
    public List<ProductOrder> findByProductName(String productName) {


        TypedQuery<ProductOrder> query = em.createQuery(
                "SELECT DISTINCT po FROM ProductOrder po JOIN po.orderItems o JOIN o.product p " +
                        "WHERE p.name= ?1", ProductOrder.class);
        query.setParameter(1, productName);
        return query.getResultList();
    }


    @Override
    public List<ProductOrder> findAll() {
        return em.createQuery("Select p from ProductOrder p", ProductOrder.class).getResultList();
    }

        @Override
        public void delete(int id){
        Optional<ProductOrder> result = findById(id);

        if(result.isPresent()) {
            em.remove(result.get());
        }

    }
}
