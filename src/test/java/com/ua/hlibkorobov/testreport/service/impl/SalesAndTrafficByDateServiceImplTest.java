package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.exception.IncorrectTimeRangeException;
import com.ua.hlibkorobov.testreport.exception.StatisticByDateNotFoundException;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import com.ua.hlibkorobov.testreport.repository.SalesAndTrafficByDateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SalesAndTrafficByDateServiceImplTest {

    @Mock
    private SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;

    @InjectMocks
    private SalesAndTrafficByDateServiceImpl salesAndTrafficByDateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAll() {
        List<SalesAndTrafficByDate> testData = new ArrayList<>();
        when(salesAndTrafficByDateRepository.saveAll(testData)).thenReturn(testData);

        List<SalesAndTrafficByDate> result = salesAndTrafficByDateService.saveAll(testData);

        assertEquals(testData, result);
        verify(salesAndTrafficByDateRepository, times(1)).saveAll(testData);
    }

    @Test
    void deleteAll() {
        salesAndTrafficByDateService.deleteAll();

        verify(salesAndTrafficByDateRepository, times(1)).deleteAll();
    }

    @Test
    void findByDate() {
        LocalDate date = LocalDate.now();
        SalesAndTrafficByDate testData = new SalesAndTrafficByDate();
        when(salesAndTrafficByDateRepository.findByDate(date)).thenReturn(Optional.of(testData));

        SalesAndTrafficByDate result = salesAndTrafficByDateService.findByDate(date);

        assertEquals(testData, result);
        verify(salesAndTrafficByDateRepository, times(1)).findByDate(date);
    }

    @Test
    void findByDate_NotFound() {
        LocalDate date = LocalDate.now();
        when(salesAndTrafficByDateRepository.findByDate(date)).thenReturn(Optional.empty());

        assertThrows(StatisticByDateNotFoundException.class, () -> salesAndTrafficByDateService.findByDate(date));
    }

    @Test
    void findBetweenDates() {
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();
        List<SalesAndTrafficByDate> testData = new ArrayList<>();
        when(salesAndTrafficByDateRepository.findEntitiesBetweenDates(startDate, endDate)).thenReturn(testData);

        List<SalesAndTrafficByDate> result = salesAndTrafficByDateService.findBetweenDates(startDate, endDate);

        assertEquals(testData, result);
        verify(salesAndTrafficByDateRepository, times(1)).findEntitiesBetweenDates(startDate, endDate);
    }

    @Test
    void findAll() {
        List<SalesAndTrafficByDate> testData = new ArrayList<>();
        when(salesAndTrafficByDateRepository.findAll()).thenReturn(testData);

        List<SalesAndTrafficByDate> result = salesAndTrafficByDateService.findAll();

        assertEquals(testData, result);
        verify(salesAndTrafficByDateRepository, times(1)).findAll();
    }

    @Test
    void update() {
        SalesAndTrafficByDateRepository mockRepository = mock(SalesAndTrafficByDateRepository.class);

        SalesAndTrafficByDateServiceImpl salesAndTrafficByDateService = new SalesAndTrafficByDateServiceImpl(mockRepository);

        List<SalesAndTrafficByDate> testData = new ArrayList<>();
        when(mockRepository.saveAll(testData)).thenReturn(testData);

        List<SalesAndTrafficByDate> result = salesAndTrafficByDateService.update(testData);

        assertEquals(testData, result);

        verify(mockRepository, times(1)).deleteAll();
        verify(mockRepository, times(1)).saveAll(testData);
    }

    @Test
    void findByDate_NullDate() {
        assertThrows(IncorrectTimeRangeException.class, () -> salesAndTrafficByDateService.findByDate(null));
        verify(salesAndTrafficByDateRepository, never()).findByDate(any());
    }

    @Test
    void findBetweenDates_IncorrectRangeDate() {
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();
        assertThrows(IncorrectTimeRangeException.class, () -> salesAndTrafficByDateService.findBetweenDates(endDate, startDate));
        verify(salesAndTrafficByDateRepository, never()).findEntitiesBetweenDates(any(), any());
    }

    @Test
    void findAll_EmptyList() {
        when(salesAndTrafficByDateRepository.findAll()).thenReturn(List.of());

        List<SalesAndTrafficByDate> result = salesAndTrafficByDateService.findAll();

        assertEquals(0, result.size());
        verify(salesAndTrafficByDateRepository, times(1)).findAll();
    }

    @Test
    void update_EmptyList() {
        SalesAndTrafficByDateRepository mockRepository = mock(SalesAndTrafficByDateRepository.class);

        SalesAndTrafficByDateServiceImpl salesAndTrafficByDateService = new SalesAndTrafficByDateServiceImpl(mockRepository);

        List<SalesAndTrafficByDate> testData = List.of();

        List<SalesAndTrafficByDate> result = salesAndTrafficByDateService.update(testData);

        assertEquals(0, result.size());

        verify(mockRepository, times(1)).deleteAll();
        verify(mockRepository, times(1)).saveAll(testData);
    }

    @Test
    void deleteAll_DoesNotThrowException() {
        doNothing().when(salesAndTrafficByDateRepository).deleteAll();

        assertDoesNotThrow(() -> salesAndTrafficByDateService.deleteAll());
        verify(salesAndTrafficByDateRepository, times(1)).deleteAll();
    }
}