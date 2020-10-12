package hello.dao;

import hello.dao.pojo.BannerInfo;
import hello.dao.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BannerRepository extends JpaRepository<BannerInfo, Long> {


    @Query("select id,gmtCreate,title,description,status,bannerUrl from BannerInfo b")
    List<BannerInfo> findAllOnlineBanner();

}
