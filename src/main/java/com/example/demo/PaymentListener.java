package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @KafkaListener(topics = "payments")
    public void onMessage(String message){
        System.out.println("Received from Kafka" + message);
    }

}
