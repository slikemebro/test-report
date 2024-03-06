package com.ua.hlibkorobov.testreport.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This class is a POJO for statistic by date.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticByDate extends SalesAndTrafficByDate {
    private LocalDate fromDate;
    private LocalDate toDate;
}