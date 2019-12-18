package com.zhu.crawler;

import com.zhu.crawler.MQ.kafkaProducer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan("com.zhu.crawler.*.mapper")
public class CrawlersApplication {



    @Autowired
    private kafkaProducer product;


//    @PostConstruct
//    public void init(){
//        for(int i=1;i<3;i++){
//            product.sendMessage("hello world"+i);
//        }
//    }


    public static void main(String[] args) {
        SpringApplication.run(CrawlersApplication.class, args);
    }

}
