package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.exception.IncorrectTimeRangeException;
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
        log.info("Saving sales and traffic by date");
        return salesAndTrafficByDateRepository.saveAll(salesAndTrafficByDateList);
    }

    @Override
    public void deleteAll() {
        salesAndTrafficByDateRepository.deleteAll();
        log.info("Deleted all sales and traffic by date");
    }

    @Override
    public SalesAndTrafficByDate findByDate(LocalDate date) {
        if (date == null){
            throw new IncorrectTimeRangeException("Start date cannot be after end date");
        }
        log.info("Finding sales and traffic by date");
        return salesAndTrafficByDateRepository.findByDate(date)
                .orElseThrow(() -> new StatisticByDateNotFoundException("Sales and traffic by date not found"));
    }

    @Override
    public List<SalesAndTrafficByDate> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)){
            throw new IncorrectTimeRangeException("Start date cannot be after end date");
        }
        log.info("Finding sales and traffic by date between dates");
        return salesAndTrafficByDateRepository.findEntitiesBetweenDates(startDate, endDate);
    }

    @Override
    public List<SalesAndTrafficByDate> findAll() {
        log.info("Finding all sales and traffic by date");
        return salesAndTrafficByDateRepository.findAll();
    }

    @Override
    public List<SalesAndTrafficByDate> update(List<SalesAndTrafficByDate> list) {
        log.info("Updating sales and traffic by date");
        deleteAll();
        return saveAll(list);
    }

}
