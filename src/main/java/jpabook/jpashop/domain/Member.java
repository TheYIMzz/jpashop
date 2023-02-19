package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // mappedBy : order 테이블에 있는 member 필드에 의해서 맵핑됨 (읽기전용)
    @OneToMany(mappedBy = "member") // 하나의 회원이 여러 개의 상품을 주문하기 떄문에 일대다
    private List<Order> orders = new ArrayList<>(); // 컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.
                                                    // => null 문제에서 안전
                                                    // => 하이버네이트는 엔티티를 영속화 할 때, 컬렉션을 감싸서 하이버네이트가
                                                    //    제공하는 내장 컬렉션으로 변경한다.


}
