package testim.service;

import testim.httpupload.domain.Cart;
import testim.httpupload.domain.ProductInOrder;
import testim.httpupload.domain.User;

import java.util.Collection;

public interface CartService {

    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
