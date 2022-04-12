package testim.httpupload;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.ItemCategory;
import testim.httpupload.service.CategoryService;
import testim.httpupload.service.ItemService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
class TestDataInitDb {

    private final ItemService itemService;

    private final CategoryService categoryService;

    private final InitService initService;


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemService.save(new Item("itemA", 10000, 10,null, "HIGHEST" , 0));
        itemService.save(new Item("itemB", 20000, 20,null, "HIGHEST",  1));

        categoryService.save(new ItemCategory("book"));
        categoryService.save(new ItemCategory("music"));


        initService.initData();

    }

    @RequiredArgsConstructor
    @Component
    @Transactional
    static class InitService {

        private final EntityManager em;

        public void initData() {
            IntStream.rangeClosed(1,30).forEach(i -> {
                Item itemEntity = getItem(i);

                em.persist(itemEntity);
            });
        }

        private Item getItem(int i) {
            Item item = new Item();
            item.setItemName("Name"+i);
            item.setItemType("HIGHEST"+i);
            item.setPrice(i);
            item.setQuantity(i);
            item.setImageFiles(null);
            item.setCategoryType(0);
            return item;
        }
    }

}