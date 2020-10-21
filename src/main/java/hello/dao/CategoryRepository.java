package hello.dao;

import hello.pojo.Category;
import hello.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where category_id = ?1", nativeQuery = true)
    Category queryCategoryByCategoryId(Long categoryId);
}
