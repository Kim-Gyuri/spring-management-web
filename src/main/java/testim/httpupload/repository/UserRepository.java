package testim.httpupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testim.httpupload.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
   // Collection<User> findAllByRole(String role);
}
