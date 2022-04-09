package testim.httpupload.service.impl;

import org.springframework.stereotype.Service;
import testim.httpupload.domain.ItemCategory;
import testim.httpupload.service.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final Map<Long, ItemCategory> store = new HashMap<>();
    private long sequence = 0L;


    @Override
    public List<ItemCategory> findAll() {
        return new ArrayList<>(store.values());
    }


    @Override
    public ItemCategory findById(Long categoryId) {
        return store.get(categoryId);
    }


    @Override
    public ItemCategory save(ItemCategory itemCategory) {
        itemCategory.setCategoryId(++sequence);
        store.put(itemCategory.getCategoryId(), itemCategory);
        return itemCategory;
    }
}
