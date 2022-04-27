package testim.httpupload.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testim.httpupload.domain.Order;

import javax.persistence.EntityManager;

@Repository
public class OrderRepository {

    @Autowired
    EntityManager em;


    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

}
