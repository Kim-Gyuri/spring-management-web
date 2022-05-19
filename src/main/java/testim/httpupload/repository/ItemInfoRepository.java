package testim.httpupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testim.httpupload.domain.Item;

@Repository
public interface ItemInfoRepository extends JpaRepository<Item, Long> {

/*
    Item findByProductId(String id);
    // onsale product
    Page<Item> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    // product in one category
    Page<Item> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<Item> findAllByOrderByProductId(Pageable pageable);
*/

}
