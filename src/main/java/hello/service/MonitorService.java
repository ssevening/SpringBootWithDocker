package hello.service;

import hello.dao.pojo.BannerInfo;
import hello.dao.pojo.MonitorInfo;

import java.util.List;

public interface MonitorService {


    public MonitorInfo save(MonitorInfo monitorInfo);

    public List<MonitorInfo> findAll();

    public void deleteById(Long id);

}
