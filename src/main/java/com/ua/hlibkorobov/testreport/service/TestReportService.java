package com.ua.hlibkorobov.testreport.service;

import com.ua.hlibkorobov.testreport.pojo.TestReport;

public interface TestReportService {
    TestReport save(TestReport testReport);

    void deleteAll();

    void update(TestReport updatedTestReport);
}
