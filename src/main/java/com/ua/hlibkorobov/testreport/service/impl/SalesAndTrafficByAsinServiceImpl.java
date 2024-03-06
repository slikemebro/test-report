package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import com.ua.hlibkorobov.testreport.repository.SalesAndTrafficByAsinRepository;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByAsinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficByAsinService {

    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;

    @Override
    public List<SalesAndTrafficByAsin> saveAll(List<SalesAndTrafficByAsin> salesAndTrafficByAsinList) {
        return salesAndTrafficByAsinRepository.saveAll(salesAndTrafficByAsinList);
    }

    @Override
    public void deleteAll() {
        salesAndTrafficByAsinRepository.deleteAll();
    }

    @Override
    public SalesAndTrafficByAsin findByAsin(String asin) {
        return salesAndTrafficByAsinRepository.findByParentAsin(asin);
    }

    @Override
    public List<SalesAndTrafficByAsin> findAll() {
        return salesAndTrafficByAsinRepository.findAll();
    }

    @Override
    public List<SalesAndTrafficByAsin> findByAsins(List<String> asins) {
        return salesAndTrafficByAsinRepository.findByParentAsinIn(asins);
    }
}
