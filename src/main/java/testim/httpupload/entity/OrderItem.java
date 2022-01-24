package testim.httpupload.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import testim.httpupload.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer orderPrice;
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    //생
    @Builder
    public OrderItem(Item item, Order order,Integer orderPrice, Integer count, Cart cart) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
        this.cart = cart;
    }

    public static OrderItem createOrderItem(Item item, Integer orderPrice, Integer count) {
        OrderItem orderItem = new OrderItem();
        orderItem.builder().item(item);
        orderItem.builder().orderPrice(orderPrice);
        orderItem.builder().count(count);

        item.removeStock(count);
        return orderItem;
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem that = (OrderItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(item.getId(), that.item.getId()) &&
                Objects.equals(item.getName(), that.item.getName()) &&
                Objects.equals(item.getCategory(), that.item.getCategory()) &&
                Objects.equals(item.getPrice(), that.item.getPrice());
    }

 */
}
