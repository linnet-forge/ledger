package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@Component
public class ResultPublisher {

    private static final Logger log = LoggerFactory.getLogger(ResultPublisher.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public ResultPublisher(KafkaTemplate<String ,String> kafkaTemplate,
                           @Value("${app.kafka.topic.result}") String topic){
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(String paymentId, String json){
        try{
            SendResult<String,String>result =
                    kafkaTemplate.send(topic,paymentId,json).get(10, TimeUnit.SECONDS);
            log.info("Result sent: paymentId={}, partition={}, offset={}",
                    paymentId,
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset());
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw  new IllegalStateException("Interrupted while sending result " + paymentId, e);
        }
        catch (ExecutionException | TimeoutException e){
            throw new IllegalStateException("Failed to send result " + paymentId, e);
        }
    }

}
