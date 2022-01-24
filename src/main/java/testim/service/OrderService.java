package testim.service;

import org.springframework.stereotype.Service;
import testim.httpupload.entity.Order;
import testim.httpupload.repository.OrderSearch;

import java.util.List;

@Service
public interface OrderService {

    Long order(Long userId, Long itemId, int count);

    void cancelOrder(Long orderId);

    List<Order> findOrders(OrderSearch orderSearch);

}
