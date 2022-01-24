package testim.httpupload;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import testim.httpupload.domain.Address;
import testim.httpupload.entity.Delivery;
import testim.httpupload.entity.Order;
import testim.httpupload.entity.OrderItem;
import testim.httpupload.entity.User;
import testim.httpupload.entity.item.Book;

import javax.persistence.EntityManager;


class TestDataInitDb {




    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            System.out.println("Init1" + this.getClass());
            User user = createUser("userA", "서울", "1", "1111");
            em.persist(user);

            Book book1 = createBook("lee", "l34", "JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("choi", "k34","JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(user);
            Order order = Order.createOrder(user, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            User user = createUser("userB", "진주", "2", "2222");

            em.persist(user);

            Book book1 = createBook("kim","k12","SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("kwang", "l12","SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(user);
            Order order = Order.createOrder(user, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private User createUser(String name, String city, String street, String zipcode) {
            User user = new User();
            user.builder().name(name);
            user.builder().address(new Address(city, street, zipcode));
            return user;
        }

        private Book createBook(String author, String isbn, String name, int price, int stockQuantity) {
            Book book1 = Book.child1()
                    .author(author)
                    .isbn(isbn)
                    .itemName(name)
                    .price(price)
                    .quantity(stockQuantity)
                    .build();
            return book1;
        }

        private Delivery createDelivery(User user) {
            Delivery delivery = new Delivery();
            delivery.builder().address(user.getAddress());
            return delivery;
        }
    }
}