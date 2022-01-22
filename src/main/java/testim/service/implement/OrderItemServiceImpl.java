package testim.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testim.httpupload.entity.OrderItem;
import testim.httpupload.entity.User;
import testim.httpupload.repository.OrderItemRepository;
import testim.service.OrderItemService;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderItem findOne(Long itemId, User user) {
        var op = user.getCart().getOrderItems().stream().filter(e -> itemId.equals(e.getId())).findFirst();
        AtomicReference<OrderItem> ref = new AtomicReference<>();
        op.ifPresent(ref::set);
        return ref.get();
    }

    @Transactional
    @Override
    public void update(Long itemId, Integer quantity, User user) {
        var op = user.getCart().getOrderItems().stream().filter(e -> itemId.equals(e.getId())).findFirst();
        op.ifPresent(orderItem -> {
            OrderItem.builder().count(quantity);
            orderItemRepository.save(orderItem);
        });
    }
}
