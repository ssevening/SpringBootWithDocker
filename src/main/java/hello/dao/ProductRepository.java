package hello.dao;

import hello.dao.pojo.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepository extends JpaRepository<ProductDetail, Long> {

    @Query(value = "select * from product_detail where product_id = ?1", nativeQuery = true)
    ProductDetail queryProductDetailByProductId(String productId);


    @Query
    Page<ProductDetail> findByProductTitleContains(String keywords, Pageable pageable);

//    @Modifying
//    @Query("update product_detail u set u.firstname = ?1 where u.lastname = ?2")
//    int setFixedFirstnameFor(String firstname, String lastname);
}
