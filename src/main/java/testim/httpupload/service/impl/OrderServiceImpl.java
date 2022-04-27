package testim.httpupload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.Order;
import testim.httpupload.domain.OrderItem;
import testim.httpupload.repository.ItemRepository;
import testim.httpupload.repository.UserRepository;
import testim.httpupload.service.ItemService;
import testim.httpupload.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

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
        System.out.println("order = " + order.getOrderItems());

        return order.getId();
    }



    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}
