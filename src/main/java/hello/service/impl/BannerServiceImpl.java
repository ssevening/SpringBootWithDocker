package hello.service.impl;

import hello.dao.BannerRepository;
import hello.dao.ProductRepository;
import hello.dao.pojo.BannerInfo;
import hello.dao.pojo.ProductDetail;
import hello.service.BannerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerRepository bannerRepository;


    @Override
    public List<BannerInfo> findAll() {
        return bannerRepository.findAll();
    }

    @Override
    public List<BannerInfo> findAllOnlineBanner() {
        return bannerRepository.findAllOnlineBanner();
    }


}
