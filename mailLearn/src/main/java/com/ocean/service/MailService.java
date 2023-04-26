package com.ocean.service;

public interface MailService {
    void send(String to, String subject, String text);
}
