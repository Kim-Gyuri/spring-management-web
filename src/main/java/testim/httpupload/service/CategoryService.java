package testim.httpupload.service;

import testim.httpupload.domain.ItemCategory;

import java.util.List;

public interface CategoryService {

    List<ItemCategory> findAll();

    ItemCategory findById(Long categoryId);


    ItemCategory save(ItemCategory itemCategory);

}
