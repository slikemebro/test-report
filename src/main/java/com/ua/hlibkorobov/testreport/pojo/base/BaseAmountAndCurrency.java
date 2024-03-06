package com.ua.hlibkorobov.testreport.pojo.base;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class for amount and currency
 */
@Getter
@Setter
public abstract class BaseAmountAndCurrency {
    private double amount;
    private String currencyCode;
}
