package testim.httpupload.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import testim.httpupload.entity.item.Item;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;
    private final Map<Long, Item> store = new HashMap<>();
    private long sequence = 0L;

    public Item save(Item item) {
        item.builder().id(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.builder()
                .itemName(updateParam.getItemName())
                .price(updateParam.getPrice())
                .quantity(updateParam.getQuantity())
                .itemType(updateParam.getItemType())
                .imageFiles(updateParam.getImageFiles())
                .build();
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
}
