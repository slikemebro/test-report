package com.ua.hlibkorobov.testreport.service;

import org.bson.Document;

import java.time.LocalDate;
import java.util.Map;

public interface ReportService {
    Document getAllReports();

    Map<String, Object> getSalesByDate(LocalDate date);

    Map<String, Object> getTrafficByDate(LocalDate date);

    Map<String, Object> getSalesByAsin(String asin);

    Map<String, Object> getTrafficByAsin(String asin);

    Map<String, Double> getTrafficFromToDate(LocalDate fromDate, LocalDate toDate);
}
