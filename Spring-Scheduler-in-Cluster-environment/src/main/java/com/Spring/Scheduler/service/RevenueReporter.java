package com.Spring.Scheduler.service;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RevenueReporter {

    @Scheduled( fixedRate = 20000)
    @SchedulerLock(name = "revenueReport", lockAtMostFor = "30m")
    public void report() {
        // report revenue based on e.g. orders in database
        System.out.println("Reporting revenue");
    }

    @Scheduled(fixedRate = 5000)
    @SchedulerLock(name = "shortRunningTask", lockAtMostFor = "50s")
    public void shortRunningTask() {
        System.out.println("Start short running task");
    }
}