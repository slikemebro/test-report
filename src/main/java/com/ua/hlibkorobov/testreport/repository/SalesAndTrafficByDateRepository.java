package com.ua.hlibkorobov.testreport.repository;

import com.ua.hlibkorobov.testreport.pojo.byDate.SalesAndTrafficByDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesAndTrafficByDateRepository extends MongoRepository<SalesAndTrafficByDate, String> {

    Optional<SalesAndTrafficByDate> findByDate(LocalDate date);

    @Query("{'date': {'$gte': ?0, '$lte': ?1}}")
    List<SalesAndTrafficByDate> findEntitiesBetweenDates(LocalDate startDate, LocalDate endDate);
}
