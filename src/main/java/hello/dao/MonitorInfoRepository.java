package hello.dao;

import hello.dao.pojo.MonitorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MonitorInfoRepository extends JpaRepository<MonitorInfo, Long> {


}
