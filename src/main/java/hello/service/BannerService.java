package hello.service;

import hello.dao.pojo.BannerInfo;
import hello.dao.pojo.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BannerService {

    /**
     * 查找所有Banner信息
     *
     * @return
     */
    public List<BannerInfo> findAll();



    public List<BannerInfo> findAllOnlineBanner();
}
