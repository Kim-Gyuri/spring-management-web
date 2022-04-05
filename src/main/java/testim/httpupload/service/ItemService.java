package testim.httpupload.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import testim.httpupload.domain.Item;

import org.springframework.data.domain.Pageable;
import testim.httpupload.repository.ItemInfoRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class ItemService {

    private final ItemInfoRepository itemInfoRepository;

    public Page<Item> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize());
        return itemInfoRepository.findAll(pageable);
    }

}
