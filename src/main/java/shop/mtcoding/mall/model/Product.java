package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity 걸려있으면 리플렉션 수행 후 클래스명으로 테이블을 만들고, 변수로 컬럼을 만들어냄
@Entity
@Getter
@Setter
//@Table을 걸면 엔티티 클래스와 데이터베이스 테이블 간의 매핑 정보를 지정할 수 있다.
@Table(name = "product_tb")
public class Product {
    //@Id는 PK를 지정할 때 사용한다.
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)는 Auto_increment 옵션을 지정할 때 사용한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;

    @ManyToOne // @ManyToOne -> FK 설정
    private Seller seller;

}
