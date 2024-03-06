package com.ua.hlibkorobov.testreport.pojo;

import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import com.ua.hlibkorobov.testreport.pojo.report.ReportSpecification;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TestReport {
    private ReportSpecification reportSpecification;
    private SalesAndTrafficByDate[] salesAndTrafficByDate;
    private SalesAndTrafficByAsin[] salesAndTrafficByAsin;
}
