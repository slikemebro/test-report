package com.ua.hlibkorobov.testreport.pojo.byAsin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesAndTrafficByAsin {
    private String parentAsin;
    private SalesByAsin salesByAsin;
    private TrafficByAsin trafficByAsin;
}
