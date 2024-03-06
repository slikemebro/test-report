package com.ua.hlibkorobov.testreport.pojo.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseAmountAndCurrency {
    private double amount;
    private String currencyCode;
}
