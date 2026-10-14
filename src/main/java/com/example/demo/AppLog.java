package com.example.demo;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class AppLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;

    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    protected AppLog() {}

    public AppLog(String level, String message) {
        this.level = level;
        this.message = message;
    }
}