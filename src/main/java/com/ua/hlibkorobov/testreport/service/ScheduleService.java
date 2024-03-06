package com.ua.hlibkorobov.testreport.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.scheduling.annotation.Scheduled;

public interface ScheduleService {
    @Scheduled(cron = "${interval-in-cron}")
    void checkForNewData();

    @PostConstruct
    void init();

    void updateData();

    @PreDestroy
    void destroy();
}
