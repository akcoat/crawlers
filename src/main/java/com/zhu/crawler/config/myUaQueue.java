package com.zhu.crawler.config;


import com.xiaoleilu.hutool.io.FileUtil;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class myUaQueue  implements InitializingBean {


    @Autowired
    private RedissonClient redissonClient;


    public void setUatoQueue(){
        RList<Object> myUA = redissonClient.getList("myUA");
        String userAgentPath = myUaQueue.class.getResource("/").getPath() + "USER-AGENT";
        List<String> uaList = FileUtil.readLines(new File(userAgentPath), "utf8");
        myUA.addAll(uaList);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        setUatoQueue();
    }
}
