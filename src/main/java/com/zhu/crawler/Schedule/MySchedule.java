package com.zhu.crawler.Schedule;


import com.zhu.crawler.crawlers.Basic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MySchedule  {


    @Autowired
    private Basic basic;

//    @Scheduled(cron = "0/5 * * * * ?")
//    public void callBasic(){
//        log.info("basic 爬虫,每五秒抓取一次数据");
//
//    }
}
