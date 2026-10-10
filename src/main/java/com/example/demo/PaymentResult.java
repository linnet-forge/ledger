package com.example.demo;

public record PaymentResult (String paymentId,
        String status,
        String failureReason){}


