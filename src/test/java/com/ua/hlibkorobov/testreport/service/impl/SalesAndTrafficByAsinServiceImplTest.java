package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import com.ua.hlibkorobov.testreport.repository.SalesAndTrafficByAsinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SalesAndTrafficByAsinServiceImplTest {

    @Mock
    private SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;

    @InjectMocks
    private SalesAndTrafficByAsinServiceImpl salesAndTrafficByAsinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAll() {
        List<SalesAndTrafficByAsin> testData = new ArrayList<>();
        when(salesAndTrafficByAsinRepository.saveAll(testData)).thenReturn(testData);

        List<SalesAndTrafficByAsin> result = salesAndTrafficByAsinService.saveAll(testData);

        assertEquals(testData, result);
        verify(salesAndTrafficByAsinRepository, times(1)).saveAll(testData);
    }

    @Test
    void deleteAll() {
        salesAndTrafficByAsinService.deleteAll();

        verify(salesAndTrafficByAsinRepository, times(1)).deleteAll();
    }

    @Test
    void findByAsin() {
        String asin = "ABC123";
        SalesAndTrafficByAsin testData = new SalesAndTrafficByAsin();
        when(salesAndTrafficByAsinRepository.findByParentAsin(asin)).thenReturn(Optional.of(testData));

        SalesAndTrafficByAsin result = salesAndTrafficByAsinService.findByAsin(asin);

        assertEquals(testData, result);
        verify(salesAndTrafficByAsinRepository, times(1)).findByParentAsin(asin);
    }

    @Test
    void findByAsins() {
        List<String> asins = Arrays.asList("ABC123", "DEF456");
        List<SalesAndTrafficByAsin> testData = new ArrayList<>();
        when(salesAndTrafficByAsinRepository.findByParentAsinIn(asins)).thenReturn(testData);

        List<SalesAndTrafficByAsin> result = salesAndTrafficByAsinService.findByAsins(asins);

        assertEquals(testData, result);
        verify(salesAndTrafficByAsinRepository, times(1)).findByParentAsinIn(asins);
    }

    @Test
    void findAll() {
        List<SalesAndTrafficByAsin> testData = new ArrayList<>();
        when(salesAndTrafficByAsinRepository.findAll()).thenReturn(testData);

        List<SalesAndTrafficByAsin> result = salesAndTrafficByAsinService.findAll();

        assertEquals(testData, result);
        verify(salesAndTrafficByAsinRepository, times(1)).findAll();
    }

    @Test
    void update() {
        List<SalesAndTrafficByAsin> testData = new ArrayList<>();
        when(salesAndTrafficByAsinRepository.saveAll(testData)).thenReturn(testData);

        List<SalesAndTrafficByAsin> result = salesAndTrafficByAsinService.update(testData);

        assertEquals(testData, result);
        verify(salesAndTrafficByAsinRepository, times(1)).deleteAll();
        verify(salesAndTrafficByAsinRepository, times(1)).saveAll(testData);
    }

    @Test
    void findByAsin_NotFound() {
        String asin = "NonExistentAsin";
        when(salesAndTrafficByAsinRepository.findByParentAsin(asin)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> salesAndTrafficByAsinService.findByAsin(asin));
    }

    @Test
    void findByAsins_EmptyAsinsList() {
        List<String> asins = new ArrayList<>();
        List<SalesAndTrafficByAsin> result = salesAndTrafficByAsinService.findByAsins(asins);

        assertTrue(result.isEmpty());
        verify(salesAndTrafficByAsinRepository, times(1)).findByParentAsinIn(anyList());
    }

    @Test
    void update_EmptyList() {
        List<SalesAndTrafficByAsin> testData = new ArrayList<>();
        when(salesAndTrafficByAsinRepository.saveAll(testData)).thenReturn(testData);

        List<SalesAndTrafficByAsin> result = salesAndTrafficByAsinService.update(testData);

        assertEquals(testData, result);
        verify(salesAndTrafficByAsinRepository, times(1)).deleteAll();
        verify(salesAndTrafficByAsinRepository, times(1)).saveAll(testData);
    }

}