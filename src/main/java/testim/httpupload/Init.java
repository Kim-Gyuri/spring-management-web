package testim.httpupload;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.ItemCategory;
import testim.httpupload.service.CategoryService;
import testim.httpupload.service.ItemService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
class TestDataInitDb {

    private final ItemService itemService;

    private final CategoryService categoryService;


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemService.save(new Item("itemA", 10000, 10,null, "HIGHEST"));
        itemService.save(new Item("itemB", 20000, 20,null, "HIGHEST"));

        categoryService.save(new ItemCategory("book"));
        categoryService.save(new ItemCategory("music"));


    }

}