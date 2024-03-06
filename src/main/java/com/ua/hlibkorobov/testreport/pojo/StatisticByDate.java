package com.ua.hlibkorobov.testreport.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticByDate extends SalesAndTrafficByDate {
    private LocalDate fromDate;
    private LocalDate toDate;
//    private double averageSalesPerOrderItemAmount;
//    private double averageSalesPerOrderItemB2BAmount;
//    private double averageSellingPriceAmount;
//    private double averageSellingPriceB2BAmount;
//    private double claimsAmount;
//    private double shippedProductSalesAmount;
}