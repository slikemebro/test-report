package com.ua.hlibkorobov.testreport.pojo.byDate;

import com.ua.hlibkorobov.testreport.pojo.base.BaseTraffic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficByDate extends BaseTraffic {
    private double orderItemSessionPercentage;
    private double orderItemSessionPercentageB2B;
    private double averageOfferCount;
    private double averageParentItems;
    private double feedbackReceived;
    private double negativeFeedbackReceived;
    private double receivedNegativeFeedbackRate;
}
