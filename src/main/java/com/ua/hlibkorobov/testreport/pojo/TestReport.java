package com.ua.hlibkorobov.testreport.pojo;

import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import com.ua.hlibkorobov.testreport.pojo.report.ReportSpecification;
import lombok.Getter;
import lombok.Setter;

/**
 * TestReport class
 * It is the main class of all data
 */
@Getter
@Setter
public class TestReport {
    private ReportSpecification reportSpecification;
    private SalesAndTrafficByDate[] salesAndTrafficByDate;
    private SalesAndTrafficByAsin[] salesAndTrafficByAsin;
}
