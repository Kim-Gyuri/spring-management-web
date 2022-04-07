package testim.httpupload.service;

import testim.httpupload.domain.ItemCategory;

import java.util.List;

public interface CategoryService {

    List<ItemCategory> findAll();

    ItemCategory findByCategoryType(Integer categoryType);

    List<ItemCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ItemCategory save(ItemCategory itemCategory);

}
