package hello.service;

import hello.dao.pojo.KeywordsInfo;
import hello.dao.pojo.MonitorInfo;

import java.util.List;

public interface KeywordsInfoService {


    public KeywordsInfo save(KeywordsInfo monitorInfo);

    public List<KeywordsInfo> findAll();

    public void deleteById(Long id);

}
