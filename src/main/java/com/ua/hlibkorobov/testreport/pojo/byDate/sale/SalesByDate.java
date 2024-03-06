package com.ua.hlibkorobov.testreport.pojo.byDate.sale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ua.hlibkorobov.testreport.pojo.base.BaseSale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesByDate extends BaseSale {
    private AverageSalesPerOrderItem averageSalesPerOrderItem;
    private AverageSalesPerOrderItemB2B averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private AverageSellingPrice averageSellingPrice;
    private AverageSellingPriceB2B averageSellingPriceB2B;
    private double unitsRefunded;
    private double refundRate;
    private double claimsGranted;
    private ClaimsAmountCurrency claimsAmount;
    private ShippedProductSales shippedProductSales;
    private double unitsShipped;
    private double ordersShipped;

}
