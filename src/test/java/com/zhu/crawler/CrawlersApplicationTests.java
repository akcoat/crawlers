package com.zhu.crawler;

import com.xiaoleilu.hutool.io.FileUtil;
import com.zhu.crawler.config.myUaQueue;

import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
class CrawlersApplicationTests {


    @Autowired
    private RedissonClient redissonClient;




//    @Test
//    void contextLoads() {
//        RList<Object> myUA = redissonClient.getList("myUA");
//        String userAgentPath = myUaQueue.class.getResource("/").getPath() + "USER-AGENT";
//        List<String> uaList = FileUtil.readLines(new File(userAgentPath), "utf8");
//        myUA.addAll(uaList);
//
//    }

}
