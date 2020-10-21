package hello.service;

import hello.pojo.Category;

import java.util.List;

public interface CategoryService {


    public Category save(Category category);

    public List<Category> findAll();

    public void deleteById(Long id);

}
