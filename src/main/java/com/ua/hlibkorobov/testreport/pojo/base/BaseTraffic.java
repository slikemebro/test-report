package com.ua.hlibkorobov.testreport.pojo.base;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class for traffic data
 */
@Getter
@Setter
public abstract class BaseTraffic {
    private double browserPageViews;
    private double browserPageViewsB2B;
    private double mobileAppPageViews;
    private double mobileAppPageViewsB2B;
    private double pageViews;
    private double pageViewsB2B;
    private double buyBoxPercentage;
    private double buyBoxPercentageB2B;
    private double unitSessionPercentage;
    private double unitSessionPercentageB2B;
    private double browserSessions;
    private double browserSessionsB2B;
    private double mobileAppSessions;
    private double mobileAppSessionsB2B;
    private double sessions;
    private double sessionsB2B;
}
