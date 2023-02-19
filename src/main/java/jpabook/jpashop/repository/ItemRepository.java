package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @Autowired
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); // id가 없으면 새 것이므로 persist()
        } else {
            em.merge(item); // id가 있으면 이미 db에 있으므로 merge()로 업데이트
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { // 단 건 조회는 find메서드를 쓰면 되지만 여러 개를 찾는 것은 jpql로 찾는다.
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
