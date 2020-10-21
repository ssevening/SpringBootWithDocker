package hello.service.impl;

import hello.dao.CategoryRepository;
import hello.pojo.Category;
import hello.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;


    @Override
    public Category save(Category category) {
        Category queryCategory = categoryRepository.queryCategoryByCategoryId(category.getCategoryId());
        if (queryCategory != null) {
            category.setId(queryCategory.getId());
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


}
