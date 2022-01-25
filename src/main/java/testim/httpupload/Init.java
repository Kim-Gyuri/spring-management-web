package testim.httpupload;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testim.httpupload.domain.Item;
import testim.httpupload.repository.ItemRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
class TestDataInitDb {

    private final ItemRepository itemRepository;


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10,null, "HIGHEST"));
        itemRepository.save(new Item("itemB", 20000, 20,null, "HIGHEST"));


    }

}