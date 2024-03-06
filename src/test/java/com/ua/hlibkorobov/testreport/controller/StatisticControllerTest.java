package com.ua.hlibkorobov.testreport.controller;

import com.ua.hlibkorobov.testreport.dto.AsinRequest;
import com.ua.hlibkorobov.testreport.pojo.StatisticByAsin;
import com.ua.hlibkorobov.testreport.pojo.StatisticByDate;
import com.ua.hlibkorobov.testreport.service.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticControllerTest {

    @Mock
    private StatisticService statisticService;

    @InjectMocks
    private StatisticController statisticController;

    @Test
    void getStatisticForSalesByDate() {
        LocalDate date = LocalDate.now();
        when(statisticService.getStatisticByDate(date)).thenReturn(new StatisticByDate(/* Your data here */));

        ResponseEntity<StatisticByDate> response = statisticController.getStatisticForSalesByDate(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(statisticService, times(1)).getStatisticByDate(date);
    }

    @Test
    void getStatisticForSalesFromToDate() {
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now();
        when(statisticService.getStatisticFromDateToDate(fromDate, toDate)).thenReturn(new StatisticByDate(/* Your data here */));

        ResponseEntity<StatisticByDate> response = statisticController.getStatisticForSalesFromToDate(fromDate, toDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(statisticService, times(1)).getStatisticFromDateToDate(fromDate, toDate);
    }

    @Test
    void getAllStatisticForSalesByDate() {
        when(statisticService.getAllStatisticByDate()).thenReturn(new StatisticByDate(/* Your data here */));

        ResponseEntity<StatisticByDate> response = statisticController.getAllStatisticForSalesByDate();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(statisticService, times(1)).getAllStatisticByDate();
    }

    @Test
    void getStatisticForSalesAsin() {
        String asin = "ABC123";
        when(statisticService.getStatisticByAsin(asin)).thenReturn(new StatisticByAsin(/* Your data here */));

        ResponseEntity<StatisticByAsin> response = statisticController.getStatisticForSalesAsin(asin);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(statisticService, times(1)).getStatisticByAsin(asin);
    }

    @Test
    void getStatisticForSalesByAsins() {
        AsinRequest asins = new AsinRequest();
        asins.setAsins(Arrays.asList("ASIN1", "ASIN2"));
        when(statisticService.getStatisticOfAsins(asins.getAsins())).thenReturn(new StatisticByAsin(/* Your data here */));

        ResponseEntity<StatisticByAsin> response = statisticController.getStatisticForSalesByAsins(asins);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(statisticService, times(1)).getStatisticOfAsins(asins.getAsins());
    }

    @Test
    void getAllStatisticForSalesByAsin() {
        when(statisticService.getAllStatisticByAsin()).thenReturn(new StatisticByAsin(/* Your data here */));

        ResponseEntity<StatisticByAsin> response = statisticController.getAllStatisticForSalesByAsin();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(statisticService, times(1)).getAllStatisticByAsin();
    }
}