package com.ua.hlibkorobov.testreport.repository;

import com.ua.hlibkorobov.testreport.pojo.byAsin.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesAndTrafficByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {

    Optional<SalesAndTrafficByAsin> findByParentAsin(String asin);

    List<SalesAndTrafficByAsin> findByParentAsinIn(List<String> asins);
}
