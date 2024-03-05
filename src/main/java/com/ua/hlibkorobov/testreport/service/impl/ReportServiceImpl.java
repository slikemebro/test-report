package com.ua.hlibkorobov.testreport.service.impl;

import com.ua.hlibkorobov.testreport.exception.DocumentNotFoundException;
import com.ua.hlibkorobov.testreport.exception.IncorrectTimeRangeException;
import com.ua.hlibkorobov.testreport.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ua.hlibkorobov.testreport.util.ConverterDate.convertToLocalDateViaInstant;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final MongoTemplate mongoTemplate;

//    @Cacheable("reports")
    @Override
    public Document getAllReports() {
        log.info("Get all reports");
        return mongoTemplate.findAll(Document.class, "test_report").get(0);
    }

    @Cacheable(value = "salesByDate", key = "#date")
    @Override
    public Map<String, Object> getSalesByDate(LocalDate date) {
        log.info("Get sales by date");
        return getSalesOrTrafficByDate(date, "salesByDate");
    }

    @Cacheable(value = "trafficByDate", key = "#date")
    @Override
    public Map<String, Object> getTrafficByDate(LocalDate date) {
        log.info("Get traffic by date");
        return getSalesOrTrafficByDate(date, "trafficByDate");
    }

    @Cacheable(value = "salesByAsin", key = "#asin")
    @Override
    public Map<String, Object> getSalesByAsin(String asin) {
        log.info("Get traffic by date");
        return getSalesOrTrafficByAsin(asin, "salesByAsin");
    }

    @Cacheable(value = "trafficByAsin", key = "#asin")
    @Override
    public Map<String, Object> getTrafficByAsin(String asin) {
        log.info("Get traffic by date");
        return getSalesOrTrafficByAsin(asin, "trafficByAsin");
    }

    @Cacheable(value = "trafficFromToDate", key = "#fromDate.toString() + #toDate.toString()")
    @Override
    public Map<String, Double> getTrafficFromToDate(LocalDate fromDate, LocalDate toDate) {
        log.info("Get traffic from date to date");
        return getSalesOrTrafficFromToDate(fromDate, toDate, "trafficByDate", "salesAndTrafficByDate");
    }

    private Map<String, Double> getSalesOrTrafficFromToDate(LocalDate fromDate,
                                                            LocalDate toDate,
                                                            String type,
                                                            String byDateOrAsin) {
        Document document = getAllReports();
        log.info("Get report");

        checkDates(fromDate, toDate, document.get("reportSpecification", Document.class));

        List<Document> salesByDateOrAsin = getSalesByDateOrAsin(document, byDateOrAsin);

        List<Document> listOfDocuments = getDocumentsFromToDate(fromDate, toDate, salesByDateOrAsin);

        Map<String, Double> statistics = new HashMap<>();

        List<Document> salesOrTraffic = getSalesOrTraffic(listOfDocuments, type);
        for (Document saleOrTraffic : salesOrTraffic) {
            Set<String> keys = saleOrTraffic.keySet();
            keys.forEach(key -> {
                if (saleOrTraffic.get(key) != null) {
                    if (statistics.containsKey(key)) {
                        statistics.merge(key, Double.valueOf(saleOrTraffic.get(key, Number.class).toString()), Double::sum);
                    } else {
                        statistics.put(key, Double.valueOf(saleOrTraffic.get(key, Number.class).toString()));
                    }
                }
            });
        }
        return statistics;
    }

    private List<Document> getSalesOrTraffic(List<Document> listOfDocuments, String type) {
        List<Document> salesOrTraffic = new ArrayList<>();
        for (Document document : listOfDocuments) {
            salesOrTraffic.add(document.get(type, Document.class));
        }
        return salesOrTraffic;
    }

    private void checkDates(LocalDate fromDate, LocalDate toDate, Document reportSpecification) {
        LocalDate reportFromDate = convertToLocalDateViaInstant(reportSpecification.getString("dataStartTime"));
        LocalDate reportToDate = convertToLocalDateViaInstant(reportSpecification.getString("dataEndTime"));
        if (fromDate.isBefore(reportFromDate) || toDate.isAfter(reportToDate) || fromDate.isAfter(toDate)) {
            log.error("Date is out of range");
            throw new IncorrectTimeRangeException("Date is out of range");
        }
    }

    private List<Document> getDocumentsFromToDate(LocalDate fromDate, LocalDate toDate, List<Document> salesByDateOrAsin) {
        List<Document> listOfDocuments = new ArrayList<>();
        for (Document documentElement : salesByDateOrAsin) {
            if (convertToLocalDateViaInstant(documentElement.getString("date")).isAfter(fromDate) &&
                    convertToLocalDateViaInstant(documentElement.getString("date")).isBefore(toDate) ||
                    convertToLocalDateViaInstant(documentElement.getString("date")).isEqual(fromDate) ||
                    convertToLocalDateViaInstant(documentElement.getString("date")).isEqual(toDate)) {
                listOfDocuments.add(documentElement);
            }
        }
        if (listOfDocuments.isEmpty()) {
            log.error("Report not found");
            throw new DocumentNotFoundException("Report not found");
        } else {
            return listOfDocuments;
        }
    }

    private Map<String, Object> getSalesOrTrafficByDate(LocalDate date, String type) {
        Document document = getAllReports();
        log.info("Get report");
        List<Document> salesByDateOrAsin = getSalesByDateOrAsin(document, "salesAndTrafficByDate");

        Document lookingFor = getDocumentByDate(date, salesByDateOrAsin);

        return getStatistics(type, lookingFor);
    }

    private Map<String, Object> getSalesOrTrafficByAsin(String asin, String type) {
        Document document = getAllReports();
        log.info("Get report");
        List<Document> salesByAsin = getSalesByDateOrAsin(document, "salesAndTrafficByAsin");

        Document lookingFor = getDocumentByAsin(asin, salesByAsin);

        return getStatistics(type, lookingFor);
    }

    private Map<String, Object> getStatistics(String type, Document lookingFor) {
        Map<String, Object> statistics = new HashMap<>();

        Document saleOrTraffic = lookingFor.get(type, Document.class);

        Set<String> keys = saleOrTraffic.keySet();
        keys.forEach(key -> {
            if (saleOrTraffic.get(key) != null) {
                statistics.put(key, saleOrTraffic.get(key));
            }
        });
        return statistics;
    }

    private Document getDocumentByAsin(String asin, List<Document> salesByAsin) {
        for (Document documentElement : salesByAsin) {
            if (documentElement.getString("parentAsin").equals(asin)) {
                return documentElement;
            }
        }
        log.error("Report not found");
        throw new DocumentNotFoundException("Report not found");
    }

    private Document getDocumentByDate(LocalDate date, List<Document> salesByDate) {
        for (Document documentElement : salesByDate) {
            if (convertToLocalDateViaInstant(documentElement.getString("date")).equals(date)) {
                return documentElement;
            }
        }
        log.error("Report not found");
        throw new DocumentNotFoundException("Report not found");
    }

    private List<Document> getSalesByDateOrAsin(Document document, String byDateOrAsin) {
        log.info("Get sales by date or asin");
        List<Document> list = document.get(byDateOrAsin, List.class);
        return list;
    }



}
