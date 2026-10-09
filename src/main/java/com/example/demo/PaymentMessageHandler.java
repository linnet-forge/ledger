package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class PaymentMessageHandler {

    private static final Logger log = LoggerFactory.getLogger(PaymentListener.class);

    public void handle(String key, String payload){

        if(payload == null || payload.isBlank()){
            log.warn("Warning, message is empty : key={}", key);
            return;
        }

        log.info("Handling message : key={}, payloadLength={}",
                key , payload.length());

    }

}
