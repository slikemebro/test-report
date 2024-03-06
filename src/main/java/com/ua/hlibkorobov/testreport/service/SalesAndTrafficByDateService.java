package com.ua.hlibkorobov.testreport.service;

import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;

import java.time.LocalDate;
import java.util.List;

public interface SalesAndTrafficByDateService {

    List<SalesAndTrafficByDate> saveAll(List<SalesAndTrafficByDate> salesAndTrafficByDateList);

    void deleteAll();

    SalesAndTrafficByDate findByDate(LocalDate date);

    List<SalesAndTrafficByDate> findBetweenDates(LocalDate startDate, LocalDate endDate);

    List<SalesAndTrafficByDate> findAll();
}
