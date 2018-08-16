package com.rich.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class MessageService {

    private final ServiceProperties serviceProperties;

    public MessageService(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public String message() {
        return this.serviceProperties.getMessage();
    }

    public String createMsg(String msg) {
        return "created " + msg;
    }
}
