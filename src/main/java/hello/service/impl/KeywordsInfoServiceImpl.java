package hello.service.impl;

import hello.dao.KeywordsInfoRepository;
import hello.dao.MonitorInfoRepository;
import hello.dao.pojo.KeywordsInfo;
import hello.dao.pojo.MonitorInfo;
import hello.service.KeywordsInfoService;
import hello.service.MonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeywordsInfoServiceImpl implements KeywordsInfoService {

    @Resource
    private KeywordsInfoRepository keywordsInfoRepository;


    @Override
    public KeywordsInfo save(KeywordsInfo keywordsInfo) {
        return keywordsInfoRepository.save(keywordsInfo);
    }

    @Override
    public List<KeywordsInfo> findAll() {
        return keywordsInfoRepository.findAll();
    }

    public void deleteById(Long id) {
        keywordsInfoRepository.deleteById(id);
    }


}
