package testim.httpupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testim.httpupload.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
