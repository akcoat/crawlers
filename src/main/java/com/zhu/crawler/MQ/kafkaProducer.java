package com.zhu.crawler.MQ;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class kafkaProducer {
    @Autowired
    private KafkaTemplate  kafkaTemplate;



    public void sendMessage (String str){
        ListenableFuture mytopic = kafkaTemplate.send("myreplication", str);
        mytopic.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                log.info("success");
                System.out.println(result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("failed"+ex.getMessage());
            }

        });
    }

    /**
     * send message async
     */
//    public void sendToKafka(final MyOutputData data) {
//        final ProducerRecord<String, String> record = createRecord(data);
//
//        ListenableFuture<SendResult<Integer, String>> future = template.send(record);
//        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
//
//            @Override
//            public void onSuccess(SendResult<Integer, String> result) {
//                handleSuccess(data);
//            }
//
//            @Override
//            public void onFailure(Throwable ex) {
//                handleFailure(data, record, ex);
//            }
//
//        });
//    }


//    /**
//     * send message sync
//     * @param data
//     */
//    public void sendToKafka(final MyOutputData data) {
//        final ProducerRecord<String, String> record = createRecord(data);
//
//        try {
//            kafkaTemplate.send(record).get(10, TimeUnit.SECONDS);
//            handleSuccess(data);
//        }
//        catch (ExecutionException e) {
//            handleFailure(data, record, e.getCause());
//        }
//        catch (TimeoutException | InterruptedException e) {
//            handleFailure(data, record, e);
//        }
//    }


}
