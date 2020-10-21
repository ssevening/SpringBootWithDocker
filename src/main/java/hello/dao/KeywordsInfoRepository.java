package hello.dao;

import hello.dao.pojo.KeywordsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface KeywordsInfoRepository extends JpaRepository<KeywordsInfo, Long> {

}
