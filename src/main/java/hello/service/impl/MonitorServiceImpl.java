package hello.service.impl;

import hello.dao.MonitorInfoRepository;
import hello.dao.pojo.MonitorInfo;
import hello.service.MonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Resource
    private MonitorInfoRepository monitorInfoRepository;


    @Override
    public MonitorInfo save(MonitorInfo monitorInfo) {
        return monitorInfoRepository.save(monitorInfo);
    }

    @Override
    public List<MonitorInfo> findAll() {
        return monitorInfoRepository.findAll();
    }

    public void deleteById(Long id) {
        monitorInfoRepository.deleteById(id);
    }


}
