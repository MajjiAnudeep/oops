package com.demo.oops.service.messenger;

import com.demo.oops.apimodel.messenger.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessengerService {
    void sendMessage(Message message);
    void startProcessing();
    List<Message> getProcessedMessages();
    void shutdown();
}
