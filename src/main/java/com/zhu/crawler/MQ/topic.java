package com.zhu.crawler.MQ;


import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class topic {
//    @Bean
//    public KafkaAdmin admin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
//                StringUtils.arrayToCommaDelimitedString(embeddedKafka().getBrokerAddresses()));
//        return new KafkaAdmin(configs);
//    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("testPartition", 4, (short) 2);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic("testReplication", 3, (short) 2);
    }

}
