package testim.httpupload.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Cart {
    @Id @GeneratedValue
    private long cartId;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private Set<OrderItem> orderItems = new HashSet<>();


    public Cart(User user) {
        this.user = user;
    }

    public Cart() {

    }

    public static Cart createCart(OrderItem... orderItems) {
        Cart cart = new Cart();
        for (OrderItem orderItem : orderItems) {
            cart.addOrderItem(orderItem);
        }
        return cart;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setCart(this);
    }
}
