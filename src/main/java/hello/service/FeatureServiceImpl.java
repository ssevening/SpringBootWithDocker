package hello.service;

import hello.dao.FeatureRepository;
import hello.dao.ProductRepository;
import hello.dao.pojo.FeatureInfo;
import hello.dao.pojo.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Resource
    private FeatureRepository featureRepository;


    @Override
    public FeatureInfo findFeatureInfoByFeaturedName(String featureName) {
        return featureRepository.findFeatureInfoByFeaturedName(featureName);
    }
}
