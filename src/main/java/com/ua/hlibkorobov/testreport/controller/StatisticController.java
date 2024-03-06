package com.ua.hlibkorobov.testreport.controller;

import com.ua.hlibkorobov.testreport.dto.AsinRequest;
import com.ua.hlibkorobov.testreport.pojo.StatisticByAsin;
import com.ua.hlibkorobov.testreport.pojo.StatisticByDate;
import com.ua.hlibkorobov.testreport.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@Log4j2
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/statistic/by-date")
    public ResponseEntity<StatisticByDate> getStatisticForSalesByDate(
            @RequestParam LocalDate date) {
        log.info("get statistic for sales by date: {}", date);
        return ResponseEntity.ok(statisticService.getStatisticByDate(date));
    }

    @GetMapping("/statistic/from-to-date")
    public ResponseEntity<StatisticByDate> getStatisticForSalesFromToDate(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate
    ) {
        log.info("get statistic for sales from date: {} to date: {}", fromDate, toDate);
        return ResponseEntity.ok(statisticService.getStatisticFromDateToDate(fromDate, toDate));
    }

    @GetMapping("/statistic/all-by-date")
    public ResponseEntity<StatisticByDate> getAllStatisticForSalesByDate() {
        log.info("get all statistic for sales by date");
        return ResponseEntity.ok(statisticService.getAllStatisticByDate());
    }

    @GetMapping("/statistic/by-asin")
    public ResponseEntity<StatisticByAsin> getStatisticForSalesAsin(
            @RequestParam String asin) {
        log.info("get statistic for sales by asin: {}", asin);
        return ResponseEntity.ok(statisticService.getStatisticByAsin(asin));
    }

    @PostMapping("/statistic/by-asins")
    public ResponseEntity<StatisticByAsin> getStatisticForSalesByAsins(
            @RequestBody AsinRequest asins
    ) {
        log.info("get statistic for sales by asins: {}", asins.getAsins());
        return ResponseEntity.ok(statisticService.getStatisticOfAsins(asins.getAsins()));
    }

    @GetMapping("/statistic/all-by-asin")
    public ResponseEntity<StatisticByAsin> getAllStatisticForSalesByAsin() {
        log.info("get all statistic for sales by asin");
        return ResponseEntity.ok(statisticService.getAllStatisticByAsin());
    }

}
