package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "categoty_id")
    private Long id;

    private String name;
    @ManyToMany
    // 중간 테이블 맵핑, 관계형 DB는 컬렉션 관계를 양쪽에 가질 수 없으므로 일대다, 다대일로 풀어내는 중간 테이블 필요
    @JoinTable(name = "Category_item",
            joinColumns = @JoinColumn(name = "category_id"), // 중간 테이블에 있는 category_id
            inverseJoinColumns = @JoinColumn(name = "item_id")) // Category_item 테이블의 item 쪽으로 들어가는 id
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

}
