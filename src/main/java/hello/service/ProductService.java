package hello.service;

import hello.dao.pojo.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    /**
     * 按照ID 来查询产品详情
     *
     * @param productId
     * @return
     */
    ProductDetail queryProductDetailByProductId(String productId);

    /**
     * 通过标题来搜索产品
     * @param keywords
     * @param pageable
     * @return
     */
    Page<ProductDetail> findByProductTitleContains(String keywords, Pageable pageable);

}
