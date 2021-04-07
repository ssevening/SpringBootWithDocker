package hello.dao;

import hello.pojo.Category;
import hello.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where product_id = ?1", nativeQuery = true)
    Product queryProductDetailByProductId(Long productId);

    @Query(value = "select id,product_title,product_id from product order by id asc limit ?1,?2", nativeQuery = true)
    List<Map<String,String>> queryProductByPageNo(int index, int pageSize);


    @Query
    Page<Product> findByProductTitleContains(String keywords, Pageable pageable);

//    @Modifying
//    @Query("update product_detail u set u.firstname = ?1 where u.lastname = ?2")
//    int setFixedFirstnameFor(String firstname, String lastname);
}
