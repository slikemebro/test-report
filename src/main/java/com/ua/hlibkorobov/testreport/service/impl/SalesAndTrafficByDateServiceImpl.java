package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.exception.StatisticByDateNotFoundException;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import com.ua.hlibkorobov.testreport.repository.SalesAndTrafficByDateRepository;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByDateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficByDateService {

    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;

    @Override
    public List<SalesAndTrafficByDate> saveAll(List<SalesAndTrafficByDate> salesAndTrafficByDateList) {
        return salesAndTrafficByDateRepository.saveAll(salesAndTrafficByDateList);
    }

    @Override
    public void deleteAll() {
        salesAndTrafficByDateRepository.deleteAll();
    }

    @Override
    public SalesAndTrafficByDate findByDate(LocalDate date) {
        return salesAndTrafficByDateRepository.findByDate(date)
                .orElseThrow(() -> new StatisticByDateNotFoundException("Sales and traffic by date not found"));
    }

    @Override
    public List<SalesAndTrafficByDate> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        return salesAndTrafficByDateRepository.findEntitiesBetweenDates(startDate, endDate);
    }

    @Override
    public List<SalesAndTrafficByDate> findAll() {
        return salesAndTrafficByDateRepository.findAll();
    }
}
