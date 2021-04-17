package hello.dao;

import hello.dao.pojo.CouponCodeInfo;
import hello.dao.pojo.User;
import hello.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CouponCodeInfoRepository extends JpaRepository<CouponCodeInfo, Long> {

    @Query(value = "select * from coupon_code_info where platform = ?1 and code= ?2", nativeQuery = true)
    CouponCodeInfo queryCouponCodeInfoByCodeAndPlatform(String platform, String code);

    @Query(value = "select * from coupon_code_info where platform = ?1", nativeQuery = true)
    List<CouponCodeInfo> queryCouponCodeInfoByPlatform(String platform);

}
