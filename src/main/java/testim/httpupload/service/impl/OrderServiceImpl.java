package testim.httpupload.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.Order;
import testim.httpupload.domain.OrderItem;
import testim.httpupload.repository.ItemRepository;
import testim.httpupload.repository.OrderRepository;
import testim.httpupload.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    private final Map<Long, Order> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Order save(Order order) {
        order.setId(++sequence);
        store.put(order.getId(), order);
        return order;
    }

    @Override
    public Order findById(Long id) {
        return store.get(id);
    }

    @Override
    public Long order(Item item, int count) {

        OrderItem orderItem = OrderItem.createOrderItem(item, count);

        Order order = Order.createOrder(orderItem);

        save(order);
        log.info("주문 후 남은 재고량={}", item.getQuantity());
        log.info("order={}", order.getOrderItems().get(0).getItem().getQuantity());

        return order.getId();
    }



    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}
