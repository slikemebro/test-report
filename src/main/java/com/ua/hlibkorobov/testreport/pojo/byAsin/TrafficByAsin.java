package com.ua.hlibkorobov.testreport.pojo.byAsin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ua.hlibkorobov.testreport.pojo.base.BaseTraffic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficByAsin extends BaseTraffic {
    private double browserSessionPercentage;
    private double browserSessionPercentageB2B;
    private double mobileAppSessionPercentage;
    private double mobileAppSessionPercentageB2B;
    private double sessionPercentage;
    private double sessionPercentageB2B;
    private double browserPageViewsPercentage;
    private double browserPageViewsPercentageB2B;
    private double mobileAppPageViewsPercentage;
    private double mobileAppPageViewsPercentageB2B;
    private double pageViewsPercentage;
    private double pageViewsPercentageB2B;
}
