package com.ua.hlibkorobov.testreport.controller;

import com.ua.hlibkorobov.testreport.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@Log4j2
public class StatisticController {

    private final ReportServiceImpl reportService;

    @GetMapping("/statistic/sales-by-date")
    public ResponseEntity<Map<String, Object>> getStatisticForSalesByDate(
            @RequestParam LocalDate date) {
        log.info("get statistic for sales by date: {}", date);
        return ResponseEntity.ok(reportService.getSalesByDate(date));
    }

    @GetMapping("/statistic/traffic-by-date")
    public ResponseEntity<Map<String, Object>> getStatisticForTrafficByDate(
            @RequestParam LocalDate date) {
        log.info("get statistic for traffic by date: {}", date);
        return ResponseEntity.ok(reportService.getTrafficByDate(date));
    }

//    @GetMapping("/statistic/sales-from-to-date")
//    public ResponseEntity<Map<String, Object>> getStatisticForSalesFromToDate(
//            @RequestParam LocalDate date) {
//        log.info("get statistic for sales by date: {}", date);
//        return ResponseEntity.ok(reportService.getSalesByDate(date));
//    }

    @GetMapping("/statistic/traffic-from-to-date")
    public ResponseEntity<Map<String, Double>> getStatisticForTrafficFromToDate(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate
    ) {
        log.info("get statistic for traffic from: {} date, to {} date", fromDate, toDate);
        return ResponseEntity.ok(reportService.getTrafficFromToDate(fromDate, toDate));
    }

    @GetMapping("/statistic/sales-by-asin")
    public ResponseEntity<Map<String, Object>> getStatisticForSalesByAsin(
            @RequestParam String asin) {
        log.info("get statistic for sales by asin: {}", asin);
        return ResponseEntity.ok(reportService.getSalesByAsin(asin));
    }

    @GetMapping("/statistic/traffic-by-asin")
    public ResponseEntity<Map<String, Object>> getStatisticForTrafficByAsin(
            @RequestParam String asin) {
        log.info("get statistic for traffic by asin: {}", asin);
        return ResponseEntity.ok(reportService.getTrafficByAsin(asin));
    }

}
