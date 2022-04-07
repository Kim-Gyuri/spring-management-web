package testim.httpupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testim.httpupload.domain.ItemCategory;

import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {

    //some category
    List<ItemCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

    //All category
    List<ItemCategory> findAllByOrderByCategoryType();

    //One category
    ItemCategory findByCategoryType(Integer categoryType);
}
