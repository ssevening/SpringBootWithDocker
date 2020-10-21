package hello.service;

import hello.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    /**
     * 按照ID 来查询产品详情
     *
     * @param productId
     * @return
     */
    Product queryProductDetailByProductId(Long productId);

    /**
     * 通过标题来搜索产品
     * @param keywords
     * @param pageable
     * @return
     */
    Page<Product> findByProductTitleContains(String keywords, Pageable pageable);

    Product save(Product product);

    /**
     * 查找所有产品
     * @return
     */
    public List<Product> findAll();

}
