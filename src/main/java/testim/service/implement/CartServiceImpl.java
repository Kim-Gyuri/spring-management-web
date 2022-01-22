package testim.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testim.httpupload.entity.Cart;
import testim.httpupload.entity.OrderItem;
import testim.httpupload.entity.User;
import testim.httpupload.repository.CartRepository;
import testim.httpupload.repository.UserRepository;
import testim.service.CartService;
import testim.service.UserService;

import java.util.Collection;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

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
    public void mergeLocalCart(Collection<OrderItem> orderItems, User user) {
        Cart finalCart = user.getCart();
        orderItems.forEach(productInOrder -> {
            List<OrderItem> list = finalCart.getOrderItems();
            list.stream().filter(e -> e.getId().equals(orderItems
        });

    }

    @Override
    public void delete(String itemId, User user) {

    }

    @Override
    public void checkout(User user) {

    }
}
