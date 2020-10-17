package hello.service.impl;

import hello.dao.ProductRepository;
import hello.pojo.Product;
import hello.service.ProductService;
import hello.utils.JsonMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public Product queryProductDetailByProductId(String productId) {
        return productRepository.queryProductDetailByProductId(productId);
    }

    public Product save(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Page<Product> findByProductTitleContains(String keywords, Pageable pageable) {
        return productRepository.findByProductTitleContains(keywords, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
