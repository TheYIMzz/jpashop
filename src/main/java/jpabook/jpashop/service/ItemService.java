package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // 클래스에 readOnly가 설정되어있기 때문에 저장에 따로 트랜잭션 설정
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void  updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 트랜잭션 안에서 엔티티를 조회해야 영속상태로 조회되고 거기서 값을 변경해야 변경 감지가 일어난다.
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
