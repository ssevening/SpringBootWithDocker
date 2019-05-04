package hello.dao;

import hello.dao.pojo.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {


    @Query("select u from User u where u.name like %?1%")
    List<User> findByFirstNameEndsWith(String firstName);


    @Query(value = "select * from User where email = ?1", nativeQuery = true)
    User findByEmailAddress(String emailAddress);


}
