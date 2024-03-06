package com.ua.hlibkorobov.testreport.pojo.byDate;

import com.ua.hlibkorobov.testreport.pojo.byDate.sale.SalesByDate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalesAndTrafficByDate {
    private LocalDate date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
