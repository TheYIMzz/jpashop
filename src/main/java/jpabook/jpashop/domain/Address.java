package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { // public은 여러 곳에서 호출할 수 있기 때문에 기본생성자를 JPA에서 허용해주는 protected로 만든다
    }

    // 값 타입은 변경이 되면 안된다.
    // 생성할 때만 값이 셋팅이 되고 Setter를 제공하지 않게 설계해서 변경이 불가능하게 한다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
