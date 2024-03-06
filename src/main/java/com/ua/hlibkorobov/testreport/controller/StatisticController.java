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
import java.util.List;

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
        return ResponseEntity.ok(statisticService.getStatisticFromDateToDate(fromDate, toDate));
    }
    @GetMapping("/statistic/all-by-date")
    public ResponseEntity<StatisticByDate> getAllStatisticForSalesByDate() {
        return ResponseEntity.ok(statisticService.getAllStatisticByDate());
    }

    @GetMapping("/statistic/by-asin")
    public ResponseEntity<StatisticByAsin> getStatisticForSalesAsin(
            @RequestParam String asin) {
        return ResponseEntity.ok(statisticService.getStatisticByAsin(asin));
    }
    @PostMapping("/statistic/by-asins")
    public ResponseEntity<StatisticByAsin> getStatisticForSalesByAsins(
            @RequestBody AsinRequest asins
    ) {
        return ResponseEntity.ok(statisticService.getStatisticOfAsins(asins.getAsins()));
    }
    @GetMapping("/statistic/all-by-asin")
    public ResponseEntity<StatisticByAsin> getAllStatisticForSalesByAsin() {
        return ResponseEntity.ok(statisticService.getAllStatisticByAsin());
    }

}
