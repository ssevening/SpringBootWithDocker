package hello.dao;

import hello.dao.pojo.FeatureInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface FeatureRepository extends JpaRepository<FeatureInfo, Long> {

    public FeatureInfo findFeatureInfoByFeaturedName(String featureName);

}
