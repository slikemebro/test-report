package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.pojo.StatisticByAsin;
import com.ua.hlibkorobov.testreport.pojo.StatisticByDate;
import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesByAsin;
import com.ua.hlibkorobov.testreport.pojo.byAsin.TrafficByAsin;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import com.ua.hlibkorobov.testreport.pojo.byDate.TrafficByDate;
import com.ua.hlibkorobov.testreport.pojo.byDate.sale.SalesByDate;
import com.ua.hlibkorobov.testreport.repository.StatisticByAsinRepository;
import com.ua.hlibkorobov.testreport.repository.StatisticByDateRepository;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByAsinService;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByDateService;
import com.ua.hlibkorobov.testreport.service.StatisticService;
import com.ua.hlibkorobov.testreport.service.TestReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticByDateRepository statisticByDateRepository;
    private final StatisticByAsinRepository statisticByAsinRepository;
    private final SalesAndTrafficByDateService salesAndTrafficByDateService;
    private final SalesAndTrafficByAsinService salesAndTrafficByAsinService;
    private final TestReportService testReportService;

    @Cacheable(value = "statisticByDate", key = "#date")
    @Override
    public StatisticByDate getStatisticByDate(LocalDate date) {
        SalesAndTrafficByDate salesAndTrafficByDate = salesAndTrafficByDateService.findByDate(date);

        StatisticByDate statisticByDate = new StatisticByDate();
        statisticByDate.setDate(date);
        statisticByDate.setSalesByDate(salesAndTrafficByDate.getSalesByDate());
        statisticByDate.setTrafficByDate(salesAndTrafficByDate.getTrafficByDate());
        log.info("Statistic by date created");
        return statisticByDateRepository.save(statisticByDate);
    }

    @Cacheable(value = "statisticFromToDate", key = "#fromDate.toString() + #toDate.toString()")
    @Override
    public StatisticByDate getStatisticFromDateToDate(LocalDate fromDate, LocalDate toDate) {
        List<SalesAndTrafficByDate> salesAndTrafficByDateList =
                salesAndTrafficByDateService.findBetweenDates(fromDate, toDate);
        StatisticByDate statisticByDate = createStatisticForDate(salesAndTrafficByDateList);
        statisticByDate.setFromDate(fromDate);
        statisticByDate.setToDate(toDate);
        log.info("Statistic from date to date created");
        return statisticByDateRepository.save(statisticByDate);
    }

    @Cacheable(value = "allStatisticByDate")
    @Override
    public StatisticByDate getAllStatisticByDate() {
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = salesAndTrafficByDateService.findAll();
        StatisticByDate statisticByDate = createStatisticForDate(salesAndTrafficByDateList);
        log.info("All statistic by date created");
        return statisticByDateRepository.save(statisticByDate);
    }

    @Cacheable(value = "statisticByAsin", key = "#asin")
    @Override
    public StatisticByAsin getStatisticByAsin(String asin) {
        SalesAndTrafficByAsin salesAndTrafficByAsin = salesAndTrafficByAsinService.findByAsin(asin);

        StatisticByAsin statisticByAsin = new StatisticByAsin();
        statisticByAsin.setParentAsin(asin);
        statisticByAsin.setSalesByAsin(salesAndTrafficByAsin.getSalesByAsin());
        statisticByAsin.setTrafficByAsin(salesAndTrafficByAsin.getTrafficByAsin());

        log.info("Statistic by asin created");
        return statisticByAsinRepository.save(statisticByAsin);
    }

    @Cacheable(value = "statisticByAsins", key = "#asins.toString()")
    @Override
    public StatisticByAsin getStatisticOfAsins(List<String> asins) {
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = salesAndTrafficByAsinService.findByAsins(asins);
        StatisticByAsin statisticByAsin = createStatisticForAsin(salesAndTrafficByAsinList);
        statisticByAsin.setParentAsins(asins.toArray(new String[0]));

        log.info("Statistic by asins created");
        return statisticByAsinRepository.save(statisticByAsin);
    }

    @Cacheable(value = "allStatisticByAsin")
    @Override
    public StatisticByAsin getAllStatisticByAsin() {
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = salesAndTrafficByAsinService.findAll();
        StatisticByAsin statisticByAsin = createStatisticForAsin(salesAndTrafficByAsinList);

        log.info("All statistic by asin created");
        return statisticByAsinRepository.save(statisticByAsin);
    }

    private StatisticByDate createStatisticForDate(List<SalesAndTrafficByDate> salesAndTrafficByDateList) {
        StatisticByDate statisticByDate = new StatisticByDate();
        statisticByDate.setSalesByDate(new SalesByDate());
        statisticByDate.setTrafficByDate(new TrafficByDate());
        for (SalesAndTrafficByDate salesAndTrafficByDate : salesAndTrafficByDateList) {

            sumFields(statisticByDate.getTrafficByDate(), salesAndTrafficByDate.getTrafficByDate());
            sumFields(statisticByDate.getSalesByDate(), salesAndTrafficByDate.getSalesByDate());
        }
        return statisticByDate;
    }

    private StatisticByAsin createStatisticForAsin(List<SalesAndTrafficByAsin> salesAndTrafficByAsinList) {
        StatisticByAsin statisticByAsin = new StatisticByAsin();
        statisticByAsin.setSalesByAsin(new SalesByAsin());
        statisticByAsin.setTrafficByAsin(new TrafficByAsin());
        for (SalesAndTrafficByAsin salesAndTrafficByAsin : salesAndTrafficByAsinList) {

            sumFields(statisticByAsin.getTrafficByAsin(), salesAndTrafficByAsin.getTrafficByAsin());
            sumFields(statisticByAsin.getSalesByAsin(), salesAndTrafficByAsin.getSalesByAsin());
        }
        return statisticByAsin;
    }


    private void sumFields(Object sumObject, Object currentObject) {
        Class<?> clazz = currentObject.getClass();

        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                Object sumValue = null;
                Object currentValue = null;
                try {
                    sumValue = field.get(sumObject);
                    currentValue = field.get(currentObject);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                if (isNumeric(field.getType())) {
                    try {
                        field.set(sumObject, ((Number) sumValue).doubleValue() + ((Number) currentValue).doubleValue());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            clazz = clazz.getSuperclass();
        }
    }

    private boolean isNumeric(Class<?> type) {
        return Number.class.isAssignableFrom(type)
                || type == int.class || type == long.class || type == float.class || type == double.class;
    }

}
