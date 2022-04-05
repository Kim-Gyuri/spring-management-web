package testim.httpupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testim.httpupload.domain.Item;

@Repository
public interface ItemInfoRepository extends JpaRepository<Item, Long> {
}
