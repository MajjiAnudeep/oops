package com.demo.oops.service.messenger;

import com.demo.oops.apimodel.messenger.IMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessagingService {
    void sendMessage(IMessage message);
    void startProcessing();
    List<IMessage> getProcessedMessages();
    void shutdown();
}
