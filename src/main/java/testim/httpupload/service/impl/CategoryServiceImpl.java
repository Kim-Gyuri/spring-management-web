package testim.httpupload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testim.httpupload.domain.ItemCategory;
import testim.httpupload.enums.ResultEnum;
import testim.httpupload.exception.MyException;
import testim.httpupload.repository.ItemCategoryRepository;
import testim.httpupload.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemCategory> findAll() {
        List<ItemCategory> res = itemCategoryRepository.findAllByOrderByCategoryType();
        return res;
    }

    @Override
    public ItemCategory findByCategoryType(Integer categoryType) {
        ItemCategory res = itemCategoryRepository.findByCategoryType(categoryType);
        if (res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    public List<ItemCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ItemCategory> res = itemCategoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        return res;
    }

    @Override
    @Transactional
    public ItemCategory save(ItemCategory itemCategory) {
        return itemCategoryRepository.save(itemCategory);
    }
}
