package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.exception.SalesAndTrafficByAsinNotFoundException;
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
        log.info("Saving sales and traffic by asin list");
        return salesAndTrafficByAsinRepository.saveAll(salesAndTrafficByAsinList);
    }

    @Override
    public void deleteAll() {
        salesAndTrafficByAsinRepository.deleteAll();
        log.info("Deleted all sales and traffic by asin");
    }

    @Override
    public SalesAndTrafficByAsin findByAsin(String asin) {
        log.info("Finding sales and traffic by asin with asin: {}", asin);
        return salesAndTrafficByAsinRepository
                .findByParentAsin(asin)
                .orElseThrow(() -> new SalesAndTrafficByAsinNotFoundException("Sales and traffic by asin with asin: " + asin + " not found"));
    }

    @Override
    public List<SalesAndTrafficByAsin> findAll() {
        log.info("Finding all sales and traffic by asin");
        return salesAndTrafficByAsinRepository.findAll();
    }

    @Override
    public List<SalesAndTrafficByAsin> findByAsins(List<String> asins) {
        log.info("Finding sales and traffic by asin with asins: {}", asins);
        return salesAndTrafficByAsinRepository.findByParentAsinIn(asins);
    }

    @Override
    public List<SalesAndTrafficByAsin> update(List<SalesAndTrafficByAsin> list) {
        log.info("Updating sales and traffic by asin list");
        deleteAll();
        return saveAll(list);
    }
}
