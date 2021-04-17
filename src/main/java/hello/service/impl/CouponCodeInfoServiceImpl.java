package hello.service.impl;

import hello.dao.CouponCodeInfoRepository;
import hello.dao.pojo.CouponCodeInfo;
import hello.service.CouponCodeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

@Service
public class CouponCodeInfoServiceImpl implements CouponCodeInfoService {

    @Resource
    CouponCodeInfoRepository couponCodeInfoRepository;

    @Override
    public CouponCodeInfo queryCouponCodeInfoByCodeAndPlatform(String platform, String code) {
        couponCodeInfoRepository.queryCouponCodeInfoByCodeAndPlatform(platform, code);
        return null;
    }

    @Override
    public void saveAndFlush(CouponCodeInfo couponCodeInfo) {
        CouponCodeInfo queryCouponCodeInfo = couponCodeInfoRepository.queryCouponCodeInfoByCodeAndPlatform(couponCodeInfo.getPlatform(), couponCodeInfo.getCode());
        if (queryCouponCodeInfo != null) {
            // 已经有了，什么也不做
            queryCouponCodeInfo.setGmtCreate(Calendar.getInstance().getTime());
            queryCouponCodeInfo.setTitle(couponCodeInfo.getTitle());
            queryCouponCodeInfo.setSubTitle(couponCodeInfo.getSubTitle());
            queryCouponCodeInfo.setImageUrl(couponCodeInfo.getImageUrl());
            queryCouponCodeInfo.setTargetUrl(couponCodeInfo.getTargetUrl());
            queryCouponCodeInfo.setInsights(couponCodeInfo.getInsights());
            couponCodeInfoRepository.save(queryCouponCodeInfo);
        } else {
            couponCodeInfoRepository.save(couponCodeInfo);
        }
    }

    @Override
    public List<CouponCodeInfo> findAll() {
        return couponCodeInfoRepository.findAll();
    }


    @Override
    public void deleteAll() {
        couponCodeInfoRepository.deleteAll();
    }

    @Override
    public List<CouponCodeInfo> queryProductDetailByPlatform(String platform) {
        return couponCodeInfoRepository.queryCouponCodeInfoByPlatform(platform);
    }


}
