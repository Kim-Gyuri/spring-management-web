package testim.httpupload.service;


import testim.httpupload.domain.Item;
import testim.httpupload.domain.Order;

import java.util.List;

public interface OrderService {

    Long order(Item item, int count);

    Order save(Order order);

    List<Order> findAll();

    Order findById(Long id);

}
