package com.ua.hlibkorobov.testreport.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ua.hlibkorobov.testreport.pojo.TestReport;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByAsinService;
import com.ua.hlibkorobov.testreport.service.SalesAndTrafficByDateService;
import com.ua.hlibkorobov.testreport.service.ScheduleService;
import com.ua.hlibkorobov.testreport.service.TestReportService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final TestReportService testReportService;
    private final SalesAndTrafficByDateService salesAndTrafficByDateService;
    private final SalesAndTrafficByAsinService salesAndTrafficByAsinService;
    private final CacheManager cacheManager;

    /**
     * Method to update data each 5 minutes.
     */
    @Override
    @Scheduled(cron = "${interval-in-cron}")
    public void updateToNewData() {
        log.info("Update date each 5 minutes.");
        updateData();
    }

    /**
     * Method to initialize data to db.
     */
    @Override
    @PostConstruct
    public void init() {
        log.info("ScheduleService initialized");
        startWatchService();

        TestReport testReport = getTestReport();

        TestReport saved = testReportService.save(testReport);
        salesAndTrafficByDateService.saveAll(Arrays.asList(saved.getSalesAndTrafficByDate()));
        salesAndTrafficByAsinService.saveAll(Arrays.asList(saved.getSalesAndTrafficByAsin()));

    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    private TestReport getTestReport() {
        try {
            ObjectMapper objectMapper = getObjectMapper();
            return objectMapper.readValue(new File("data/test_report.json"), TestReport.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to start watch service for file changes.
     * If file was modified, then update data.
     */
    private void startWatchService() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Path directoryToWatch = Paths.get("data/");
            directoryToWatch.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            Thread watchThread = new Thread(() -> {
                while (true) {
                    try {
                        WatchKey key = watchService.take();
                        for (WatchEvent<?> event : key.pollEvents()) {
                            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                                log.info("File modified: " + event.context());
                                updateData();
                                break;
                            }
                        }

                        boolean isValid = key.reset();
                        if (!isValid) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        log.error("Error occurred while watching for file changes", e);
                    }
                }
            });

            watchThread.start();
        } catch (Exception e) {
            log.error("Error occurred while starting watch service", e);
        }
    }

    /**
     * Method to update data in db.
     * And clear cache.
     */
    @Override
    public void updateData() {
        log.info("update Data");
        clearCache();

        TestReport testReport = getTestReport();
        testReportService.update(testReport);
        salesAndTrafficByDateService.update(Arrays.asList(testReport.getSalesAndTrafficByDate()));
        salesAndTrafficByAsinService.deleteAll();
        salesAndTrafficByAsinService.saveAll(Arrays.asList(testReport.getSalesAndTrafficByAsin()));
        salesAndTrafficByAsinService.update(Arrays.asList(testReport.getSalesAndTrafficByAsin()));
    }

    private void clearCache() {
        for (String cacheName : cacheManager.getCacheNames()) {
            cacheManager.getCache(cacheName).clear();
        }
    }

    /**
     * Method to destroy ScheduleService and delete all data from db.
     */
    @Override
    @PreDestroy
    public void destroy() {
        testReportService.deleteAll();
        salesAndTrafficByDateService.deleteAll();
        salesAndTrafficByAsinService.deleteAll();
        log.info("ScheduleService destroyed and all data deleted");
    }
}
