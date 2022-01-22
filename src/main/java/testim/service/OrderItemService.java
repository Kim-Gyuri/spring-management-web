package testim.service;

import testim.httpupload.entity.OrderItem;
import testim.httpupload.entity.User;

public interface OrderItemService {

    void update(Long itemId, Integer quantity, User user);
    OrderItem findOne(Long itemId, User user);

}
