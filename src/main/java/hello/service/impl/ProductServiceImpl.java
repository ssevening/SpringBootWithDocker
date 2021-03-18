package hello.service.impl;

import hello.dao.ProductRepository;
import hello.pojo.Category;
import hello.pojo.Product;
import hello.service.ProductService;
import hello.utils.JsonMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public Product queryProductDetailByProductId(Long productId) {
        return productRepository.queryProductDetailByProductId(productId);
    }

    @Override
    public Product save(Product product) {
        Product queryProduct = productRepository.queryProductDetailByProductId(product.getProductId());
        if (queryProduct != null) {
            product.setId(queryProduct.getId());
        }
        product.webToDB();
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsByPageNo(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Product> products = productRepository.findAll(pageable);
        return products;
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
