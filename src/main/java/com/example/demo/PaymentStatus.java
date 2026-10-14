package com.example.demo;

public enum PaymentStatus {

    PENDING("Pending"), SENT("Sent"), COMPLETED("Completed"), FAILED("Failed");

    private final String wireName;
    PaymentStatus(String wireName){
        this.wireName = wireName;
    }

    public String wireName(){return wireName;}

}
