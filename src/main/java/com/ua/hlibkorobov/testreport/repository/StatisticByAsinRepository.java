package com.ua.hlibkorobov.testreport.repository;

import com.ua.hlibkorobov.testreport.pojo.StatisticByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticByAsinRepository extends MongoRepository<StatisticByAsin, String> {

}
