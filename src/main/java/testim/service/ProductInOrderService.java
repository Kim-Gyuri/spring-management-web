package testim.service;

import testim.httpupload.domain.ProductInOrder;
import testim.httpupload.domain.User;

public interface ProductInOrderService {

    void update(Long itemId, Integer quantity, User user);
    ProductInOrder findOne(Long itemId, User user);

}
