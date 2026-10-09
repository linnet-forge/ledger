package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class PaymentListener {
    private static final Logger log = LoggerFactory.getLogger(PaymentListener.class);

    private final PaymentMessageHandler handler;

    public PaymentListener(PaymentMessageHandler handler){
        this.handler = handler;
    }

    @KafkaListener(topics = "payments")
    public void onMessage(ConsumerRecord <String,String> record){

        log.info("Received message: topic={}, partition={}, offset={}, key={}",
                record.topic(),record.partition(),record.offset(),record.key()
        );


    }

}
