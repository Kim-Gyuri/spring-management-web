package testim.httpupload.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.Order;
import testim.httpupload.exception.NotEnoughStockException;
import testim.httpupload.repository.OrderRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품() throws Exception {
        //given


        Item book = createItem("시골 JPA", 10000, 10);

        int orderCount = 2;


        Long save = orderService.order(book, orderCount);
        List<Order> all = orderService.findAll();
        for (Order order1 : all) {
            System.out.println("order1.getOrderItems().get(0).getItem().getItemName() = " + order1.getOrderItems().get(0).getItem().getItemName());
            System.out.println("order1.getOrderItems().get(0).getItem().count = " + order1.getOrderItems().get(0).getCount());
            System.out.println("order1.getOrderItems().size() = " + order1.getOrderItems().size());
        }

    }

    @Test
    public void 상품주문() throws Exception {
        //given


        Item book = createItem("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order( book, orderCount);

        //then

        //Order getOrder = orderRepository.findOne(orderId);
        Order getOrder = orderService.findById(orderId);

        //     assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        System.out.println("getOrder = " + getOrder.getOrderItems().size());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Item item = createItem("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
        //orderService.order(item, orderCount);

        //then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(item, orderCount);
        });
        //fail("재고 수량 부족 예외가 발행해야 한다.");
    }

    private Item createItem(String name, int price, int stockQuantity) {
        Item book = new Item();
        book.setItemName(name);
        book.setPrice(price);
        book.setQuantity(stockQuantity);

        em.persist(book);
        return book;
    }

}