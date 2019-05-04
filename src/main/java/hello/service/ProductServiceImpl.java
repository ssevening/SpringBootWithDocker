package hello.service;

import hello.dao.ProductRepository;
import hello.dao.pojo.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public ProductDetail queryProductDetailByProductId(String productId) {
        return productRepository.queryProductDetailByProductId(productId);
    }

    @Override
    public Page<ProductDetail> findByProductTitleContains(String keywords, Pageable pageable) {
        return productRepository.findByProductTitleContains(keywords, pageable);
    }
}
