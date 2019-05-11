package hello.service;

import hello.dao.pojo.FeatureInfo;
import hello.dao.pojo.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeatureService {

    void addFeatureInfo(List<FeatureInfo> features);

    /**
     * 按照名称类型来查询新品信息
     *
     * @param featureName
     * @return
     */
    FeatureInfo findFeatureInfoByFeaturedName(String featureName);

}
