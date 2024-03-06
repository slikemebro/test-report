package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.pojo.TestReport;
import com.ua.hlibkorobov.testreport.repository.TestReportRepository;
import com.ua.hlibkorobov.testreport.service.TestReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class TestReportServiceImpl implements TestReportService {

    private final TestReportRepository testReportRepository;

    @Override
    public TestReport save(TestReport testReport) {
        log.info("Save test report");
        return testReportRepository.save(testReport);
    }

    @Override
    public void deleteAll() {
        testReportRepository.deleteAll();
        log.info("Delete all test reports");
    }

    @Override
    public void update(TestReport updatedTestReport) {
        log.info("Update test report");
        deleteAll();
        save(updatedTestReport);
    }
}
