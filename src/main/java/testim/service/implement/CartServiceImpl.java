package testim.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testim.httpupload.entity.Cart;
import testim.httpupload.entity.Delivery;
import testim.httpupload.entity.OrderItem;
import testim.httpupload.entity.User;
import testim.httpupload.enums.DeliveryStatus;
import testim.httpupload.enums.ResultEnum;
import testim.httpupload.exception.MyException;
import testim.httpupload.repository.CartRepository;
import testim.httpupload.repository.OrderItemRepository;
import testim.httpupload.repository.UserRepository;
import testim.service.CartService;
import testim.service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<OrderItem> orderItems, User user) {
        Cart finalCart = user.getCart();
        orderItems.forEach(orderItem -> {
            List<OrderItem> list = finalCart.getOrderItems();
            Optional<OrderItem> old = list.stream().filter(e -> e.getItem().getId().equals(orderItem.getId())).findFirst();
            OrderItem isOrderItem;
            if (old.isPresent()) {
                isOrderItem = old.get();
                isOrderItem.builder().count(orderItem.getCount() + isOrderItem.getCount());
            } else {
                isOrderItem = orderItem;
                isOrderItem.builder().cart(finalCart);
                finalCart.getOrderItems().add(isOrderItem);
            }
            orderItemRepository.save(isOrderItem);
        });
        cartRepository.save(finalCart);
    }

    @Override
    @Transactional
    public void delete(Long itemId, User user) {
        if (itemId.equals("") || user == null) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }
        var op = user.getCart().getOrderItems().stream().filter(e -> itemId.equals(e.getItem().getId())).findFirst();
        op.ifPresent(orderItem -> {
            orderItem.builder().cart(null);
            orderItemRepository.deleteById(orderItem.getId());
        });
    }

    @Override
    @Transactional
    public void checkout(User user) {

        //배송정보 생성
        Delivery.builder().address(user.getAddress());
        Delivery.builder().status(DeliveryStatus.READY);





    }
}
