package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.pojo.StatisticByAsin;
import com.ua.hlibkorobov.testreport.pojo.StatisticByDate;
import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import com.ua.hlibkorobov.testreport.repository.StatisticByAsinRepository;
import com.ua.hlibkorobov.testreport.repository.StatisticByDateRepository;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByAsinService;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByDateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {

    @Mock
    private StatisticByDateRepository statisticByDateRepository;

    @Mock
    private StatisticByAsinRepository statisticByAsinRepository;

    @Mock
    private SalesAndTrafficByDateService salesAndTrafficByDateService;

    @Mock
    private SalesAndTrafficByAsinService salesAndTrafficByAsinService;

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Test
    public void testGetStatisticByDate() {
        LocalDate date = LocalDate.now();
        SalesAndTrafficByDate salesAndTrafficByDate = new SalesAndTrafficByDate();
        when(salesAndTrafficByDateService.findByDate(date)).thenReturn(salesAndTrafficByDate);

        statisticService.getStatisticByDate(date);

        verify(salesAndTrafficByDateService, times(1)).findByDate(date);
        verify(statisticByDateRepository, times(1)).save(any(StatisticByDate.class));
    }

    @Test
    public void testGetStatisticFromDateToDate() {
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now();
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = Arrays.asList(
                new SalesAndTrafficByDate(), new SalesAndTrafficByDate()
        );
        when(salesAndTrafficByDateService.findBetweenDates(fromDate, toDate)).thenReturn(salesAndTrafficByDateList);

        statisticService.getStatisticFromDateToDate(fromDate, toDate);

        verify(salesAndTrafficByDateService, times(1)).findBetweenDates(fromDate, toDate);
        verify(statisticByDateRepository, times(1)).save(any(StatisticByDate.class));
    }

    @Test
    public void testGetAllStatisticByDate() {
        List<SalesAndTrafficByDate> salesAndTrafficByDateList = Collections.singletonList(new SalesAndTrafficByDate());
        when(salesAndTrafficByDateService.findAll()).thenReturn(salesAndTrafficByDateList);

        statisticService.getAllStatisticByDate();

        verify(salesAndTrafficByDateService, times(1)).findAll();
        verify(statisticByDateRepository, times(1)).save(any(StatisticByDate.class));
    }

    @Test
    public void testGetStatisticByAsin() {
        String asin = "ABC123";
        SalesAndTrafficByAsin salesAndTrafficByAsin = new SalesAndTrafficByAsin();
        when(salesAndTrafficByAsinService.findByAsin(asin)).thenReturn(salesAndTrafficByAsin);

        statisticService.getStatisticByAsin(asin);

        verify(salesAndTrafficByAsinService, times(1)).findByAsin(asin);
        verify(statisticByAsinRepository, times(1)).save(any(StatisticByAsin.class));
    }

    @Test
    public void testGetStatisticOfAsins() {
        List<String> asins = Arrays.asList("ABC123", "DEF456");
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = Arrays.asList(
                new SalesAndTrafficByAsin(), new SalesAndTrafficByAsin()
        );
        when(salesAndTrafficByAsinService.findByAsins(asins)).thenReturn(salesAndTrafficByAsinList);

        statisticService.getStatisticOfAsins(asins);

        verify(salesAndTrafficByAsinService, times(1)).findByAsins(asins);
        verify(statisticByAsinRepository, times(1)).save(any(StatisticByAsin.class));
    }

    @Test
    public void testGetAllStatisticByAsin() {
        List<SalesAndTrafficByAsin> salesAndTrafficByAsinList = Collections.singletonList(new SalesAndTrafficByAsin());
        when(salesAndTrafficByAsinService.findAll()).thenReturn(salesAndTrafficByAsinList);

        statisticService.getAllStatisticByAsin();

        verify(salesAndTrafficByAsinService, times(1)).findAll();
        verify(statisticByAsinRepository, times(1)).save(any(StatisticByAsin.class));
    }

    @Test
    public void testGetStatisticFromDateToDateWithEmptyResult() {
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now();
        when(salesAndTrafficByDateService.findBetweenDates(fromDate, toDate)).thenReturn(Collections.emptyList());

        statisticService.getStatisticFromDateToDate(fromDate, toDate);

        verify(salesAndTrafficByDateService, times(1)).findBetweenDates(fromDate, toDate);
    }

    @Test
    public void testGetStatisticOfAsinsWithEmptyResult() {
        List<String> asins = Arrays.asList("ABC123", "DEF456");
        when(salesAndTrafficByAsinService.findByAsins(asins)).thenReturn(Collections.emptyList());

        statisticService.getStatisticOfAsins(asins);

        verify(salesAndTrafficByAsinService, times(1)).findByAsins(asins);
    }

}