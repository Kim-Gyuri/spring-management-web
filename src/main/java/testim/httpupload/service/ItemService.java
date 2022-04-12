package testim.httpupload.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import testim.httpupload.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item findById(Long id);

    Item save(Item item);

    void update(Long itemId, Item updateParam);

    void delete(Long id);

    Page<Item> findItemList(Pageable pageable);


}
