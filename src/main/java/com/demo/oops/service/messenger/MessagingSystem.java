package com.demo.oops.service.messenger;

import org.springframework.stereotype.Service;

@Service
public interface MessagingSystem {
    void startMessaging(int users);
}
