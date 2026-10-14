package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;




@Service
public class PaymentMessageHandler {

    private static final Logger log = LoggerFactory.getLogger(PaymentMessageHandler.class);
    private final ResultPublisher publisher;
    private final ObjectMapper mapper;
    private final PaymentRepository paymentRepository;
    private final AppLogRepository appLogRepository;
    public PaymentMessageHandler(ResultPublisher publisher, ObjectMapper mapper, PaymentRepository paymentRepository, AppLogRepository appLogRepository) {
        this.publisher = publisher;
        this.mapper = mapper;
        this.paymentRepository = paymentRepository;
        this.appLogRepository = appLogRepository;
    }

    public void handle(String key, String payload){

        if(payload == null || payload.isBlank()){
            log.warn("Warning, message is empty : key={}", key);
            return;
        }

        log.info("Handling message : key={}, payloadLength={}",
                key , payload.length());


        PaymentResult result = new PaymentResult(key, PaymentStatus.COMPLETED); // заглушка пока что. чтобы не выебывался
        publisher.publish(key, mapper.writeValueAsString(result));
    }
    }


