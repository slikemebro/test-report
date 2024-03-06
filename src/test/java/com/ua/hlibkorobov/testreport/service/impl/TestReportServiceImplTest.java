package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.pojo.TestReport;
import com.ua.hlibkorobov.testreport.repository.TestReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestReportServiceImplTest {
    @Mock
    private TestReportRepository testReportRepository;

    @InjectMocks
    private TestReportServiceImpl testReportService;

    @Test
    void saveTestReport_ShouldSaveSuccessfully() {
        TestReport testReport = new TestReport();
        when(testReportRepository.save(testReport)).thenReturn(testReport);

        TestReport savedTestReport = testReportService.save(testReport);

        verify(testReportRepository, times(1)).save(testReport);
        assertEquals(testReport, savedTestReport);
    }

    @Test
    void deleteAllTestReports_ShouldDeleteSuccessfully() {
        testReportService.deleteAll();

        verify(testReportRepository, times(1)).deleteAll();
    }

    @Test
    void updateTestReport_ShouldUpdateSuccessfully() {
        TestReport updatedTestReport = new TestReport();

        testReportService.update(updatedTestReport);

        verify(testReportRepository, times(1)).deleteAll();
        verify(testReportRepository, times(1)).save(updatedTestReport);
    }

}