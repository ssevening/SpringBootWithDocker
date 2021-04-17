package hello.service;

import hello.dao.pojo.CouponCodeInfo;
import hello.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CouponCodeInfoService {

    /**
     * 用来查询产coupon 信息
     *
     * @param code
     * @return
     */
    CouponCodeInfo queryCouponCodeInfoByCodeAndPlatform(String platform, String code);


    void saveAndFlush(CouponCodeInfo couponCodeInfo);

    List<CouponCodeInfo> findAll();

    void deleteAll();

    List<CouponCodeInfo> queryProductDetailByPlatform(String platform);


}
