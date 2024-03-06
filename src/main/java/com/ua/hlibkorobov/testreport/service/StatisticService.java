package com.ua.hlibkorobov.testreport.service;

import com.ua.hlibkorobov.testreport.pojo.StatisticByAsin;
import com.ua.hlibkorobov.testreport.pojo.StatisticByDate;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {

    StatisticByDate getStatisticByDate(LocalDate date);

    StatisticByDate getStatisticFromDateToDate(LocalDate fromDate, LocalDate toDate);

    StatisticByDate getAllStatisticByDate();

    StatisticByAsin getStatisticByAsin(String asin);

    StatisticByAsin getStatisticOfAsins(List<String> asins);

    StatisticByAsin getAllStatisticByAsin();

}
