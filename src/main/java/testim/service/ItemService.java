package testim.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import testim.httpupload.domain.Item;

public interface ItemService {

    Item findOne(Long itemId);

    Page<Item> findUpAll(Pageable pageable);

    Page<Item> findAll(Pageable pageable);

    Page<Item> findAllInCategory(Integer categoryType, Pageable pageable);

    void increaseStock(Long itemId, Integer amount);

    void decreaseStock(Long itemId, Integer amount);

    Item offSale(Long itemId);

    Item onSale(Long itemId);

    Item update(Item item);

    Item save(Item item);

    void delete(Long itemId);
}
