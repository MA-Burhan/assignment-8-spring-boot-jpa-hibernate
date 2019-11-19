package se.lexicon.amin.springboot_jpa_assignment.data;

import org.springframework.stereotype.Repository;
import se.lexicon.amin.springboot_jpa_assignment.entity.AppUser;
import se.lexicon.amin.springboot_jpa_assignment.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @PersistenceContext
    EntityManager em;


    @Override
    public OrderItem save(OrderItem orderItem) {
        if (orderItem.getId() == 0) {
            em.persist(orderItem);
        } else {
            em.merge(orderItem);
        }

        return orderItem;
    }


    @Override
    public Optional<OrderItem> findById(int id) {

        return Optional.ofNullable(em.find(OrderItem.class, id));
    }


    @Override
    public List<OrderItem> findAll() {
        return em.createQuery("Select o from OrderItem o", OrderItem.class).getResultList();
    }


    @Override
    public void delete(int id){
        Optional<OrderItem> result = findById(id);

        if(result.isPresent()) {
            em.remove(result.get());
        }

    }
}
