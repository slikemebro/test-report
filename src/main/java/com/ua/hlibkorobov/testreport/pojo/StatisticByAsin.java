package com.ua.hlibkorobov.testreport.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is a POJO for statistic by ASIN
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticByAsin extends SalesAndTrafficByAsin {
    private String[] parentAsins;
}
