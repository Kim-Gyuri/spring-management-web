package testim.httpupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testim.httpupload.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
