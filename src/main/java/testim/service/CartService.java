package testim.service;

import testim.httpupload.entity.Cart;
import testim.httpupload.entity.OrderItem;
import testim.httpupload.entity.User;

import java.util.Collection;

public interface CartService {

    Cart getCart(User user);

    void mergeLocalCart(Collection<OrderItem> orderItems, User user);

    void delete(Long itemId, User user);

    void checkout(User user);
}
