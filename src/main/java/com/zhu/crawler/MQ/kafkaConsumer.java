package com.zhu.crawler.MQ;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class kafkaConsumer {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @KafkaListener(topics="myreplication")
    public  void consumer(ConsumerRecord<String,String> consumerRecord){
        System.out.println(consumerRecord);
    }



//    @KafkaListener(topics="myreplication")
//    public  void     consumer(@Payload String foo,
//                              @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
//                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
//                              @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                              @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
//                              Acknowledgment acknowledgment){
//
////        Optional<Object> kafkaMassage = Optional.ofNullable(record.value());
////        if(kafkaMassage.isPresent()){
////            Object o = kafkaMassage.get();
////            System.out.println(o);
////        }
////        return "wrong the world ";
//      //  return "hello world;nihao shijie ";
//
//        System.out.println(foo+","+key+","+partition+","+topic+","+ts);
//        acknowledgment.acknowledge();
//
//    }

}
