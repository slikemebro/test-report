package com.ua.hlibkorobov.testreport.service;

import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;

import java.util.List;

public interface SalesAndTrafficByAsinService {

    List<SalesAndTrafficByAsin> saveAll(List<SalesAndTrafficByAsin> salesAndTrafficByAsinList);

    void deleteAll();

    SalesAndTrafficByAsin findByAsin(String asin);

    List<SalesAndTrafficByAsin> findAll();

    List<SalesAndTrafficByAsin> findByAsins(List<String> asins);

    List<SalesAndTrafficByAsin> update(List<SalesAndTrafficByAsin> list);
}
