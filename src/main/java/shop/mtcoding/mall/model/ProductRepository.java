package shop.mtcoding.mall.model;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository // 컴퍼넌트 스캔. Repository는 DAO의 역할
public class ProductRepository {


    @Autowired
    //커넥션 객체 관리
    private EntityManager em; //오브젝트 매핑을 엔티티만 해준다.


    public  ProductDTO findByIdDTO(int id){
        //조회 할 때는 매핑할 클래스를 적어준다(ProductDTO.class)
        Query query = em.createNativeQuery("select id, name, price, qty, '설명' des from product_tb where id = :id");
        query.setParameter("id", id);

        JpaResultMapper mapper = new JpaResultMapper();
        ProductDTO productDTO = mapper.uniqueResult(query, ProductDTO.class);
        return productDTO;

    }

    @Transactional
    public void save(String name, int price, int qty){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }

    @Transactional
    public void saveWithFK(String name, int price, int qty, int sellerId){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty, seller_id) values(:name, :price, :qty, :sellerId)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.setParameter("sellerId",sellerId);
        query.executeUpdate();
    }

    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        //execute query와 유사한 역할
        List<Product> productList = query.getResultList();
        return productList;

    }

    public Product findbyId(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
        //execute query와 유사한 역할\
        query.setParameter("id", id);
        Product product = (Product)query.getSingleResult();
        return product;

    }

    public Product findById2(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id");
        query.setParameter("id", id);
        // row가 1건
        // 1, 바나나, 1000, 50
        Object[] object = (Object[]) query.getSingleResult();
        int id2 = (int) object[0];
        String name2 = (String) object[1];
        int price2 = (int) object[2];
        int qty2 = (int) object[3];

        Product product = new Product();
        product.setId(id2);
        product.setName(name2);
        product.setPrice(price2);
        product.setQty(qty2);
        return product;
    }

    public Product findByIdJoinSeller(int id){
        Query query = em.createNativeQuery("select * \n" +
                "from product_tb pt\n" +
                "inner join seller_tb st on pt.seller_id = st.id\n" +
                "where pt.id = :id", Product.class);
        //execute query와 유사한 역할\
        query.setParameter("id", id);
        Product product = (Product)query.getSingleResult();
        return product;
    }
    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from product_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Transactional
    public void updateBy(int id, String name, int price, int qty){
        Query query = em.createNativeQuery("update product_tb set name = :name, price = :price, qty = :qty where id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();

    }
}