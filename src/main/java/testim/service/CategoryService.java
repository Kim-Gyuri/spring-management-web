package testim.service;

import testim.httpupload.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findByCategoryType(Integer categoryType);

    List<Category> findByCategoryTypeIn(List<Integer> CategoryTypeList);

    Category save(Category category);
}
