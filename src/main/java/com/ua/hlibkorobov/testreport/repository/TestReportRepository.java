package com.ua.hlibkorobov.testreport.repository;

import com.ua.hlibkorobov.testreport.pojo.TestReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestReportRepository extends MongoRepository<TestReport, String> {

}
