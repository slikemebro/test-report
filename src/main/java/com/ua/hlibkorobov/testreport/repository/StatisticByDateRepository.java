package com.ua.hlibkorobov.testreport.repository;

import com.ua.hlibkorobov.testreport.pojo.StatisticByDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StatisticByDateRepository
        extends MongoRepository<StatisticByDate, String> {

    Optional<StatisticByDate> findByDate(LocalDate date);
}
