package testim.httpupload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import testim.httpupload.domain.Item;
import testim.httpupload.repository.ItemInfoRepository;
import testim.httpupload.repository.ItemRepository;
import testim.httpupload.service.CategoryService;
import testim.httpupload.service.ItemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemInfoRepository itemInfoRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemRepository itemRepository;

    private final Map<Long, Item> store = new HashMap<>();
    private long sequence = 0L;


    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Item findById(Long id) {
        return store.get(id);
    }

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setItemType(updateParam.getItemType());
        findItem.setItemType(updateParam.getItemType());
        findItem.setImageFiles(updateParam.getImageFiles());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public Page<Item> findItemList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
               10);
        return itemRepository.findAll(pageable);
    }
}
