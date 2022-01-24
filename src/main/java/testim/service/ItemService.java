package testim.service;


import testim.httpupload.entity.item.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    void updateItem(Long itemId, String name, int price, int stockQuantity);

    List<Item> findItems();

    Item findOne(Long itemId);

    void findById(Long id);

    /*
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

     */
}