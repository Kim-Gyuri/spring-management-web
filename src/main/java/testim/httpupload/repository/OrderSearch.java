package testim.httpupload.repository;

import lombok.Getter;
import testim.httpupload.enums.OrderStatus;

@Getter
public class OrderSearch {

    private String userName;
    private OrderStatus orderStatus;
}
