package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Table을 걸면 엔티티 클래스와 데이터베이스 테이블 간의 매핑 정보를 지정할 수 있다.
@Table(name = "seller_tb")
public class Seller {
    //@Id는 PK를 지정할 때 사용한다.
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)는 Auto_increment 옵션을 지정할 때 사용한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;

}
