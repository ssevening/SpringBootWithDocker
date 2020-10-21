package hello.dao;

import hello.pojo.Category;
import hello.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where product_id = ?1", nativeQuery = true)
    Product queryProductDetailByProductId(Long productId);


    @Query
    Page<Product> findByProductTitleContains(String keywords, Pageable pageable);

//    @Modifying
//    @Query("update product_detail u set u.firstname = ?1 where u.lastname = ?2")
//    int setFixedFirstnameFor(String firstname, String lastname);
}
