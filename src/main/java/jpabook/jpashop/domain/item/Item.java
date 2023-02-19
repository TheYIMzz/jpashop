package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStrockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 싱글테이블 안에서 구분용
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==// : stockQuantity를 변경해야 할 일이 있으면 바깥에서 계산해서 Setter로 넣는게 아니라
    //                      핵심 비즈니스 메서드를 가지고 변경하는게 객체지향적이다
    // stock 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStrockException("need more stock");
        }
        this.stockQuantity = restStock;

    }
}
