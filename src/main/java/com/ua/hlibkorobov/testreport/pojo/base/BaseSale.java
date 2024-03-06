package com.ua.hlibkorobov.testreport.pojo.base;

import com.ua.hlibkorobov.testreport.pojo.byDate.sale.OrderedProductSales;
import com.ua.hlibkorobov.testreport.pojo.byDate.sale.OrderedProductSalesB2B;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for sales
 */
@Getter
@Setter
public abstract class BaseSale {
    private double unitsOrdered;
    private double unitsOrderedB2B;
    private OrderedProductSales orderedProductSales;
    private OrderedProductSalesB2B orderedProductSalesB2B;
    private double totalOrderItems;
    private double totalOrderItemsB2B;
}
