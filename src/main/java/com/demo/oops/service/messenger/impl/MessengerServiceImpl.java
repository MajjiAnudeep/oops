package com.demo.oops.service.messenger.impl;

import com.demo.oops.apimodel.messenger.Message;
import com.demo.oops.service.messenger.AbstractMessengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@Slf4j
@Component
public class MessengerServiceImpl extends AbstractMessengerService {
    public MessengerServiceImpl(BlockingQueue<Message> messageQueue, ExecutorService executorService) {
        super(messageQueue, executorService);
    }

    @Override
    protected void preprocessMessage(Message message) {
        // Example: Logging message before processing
        log.info("Preprocessing message: {}", message.getContent());
    }

    @Override
    protected void postprocessMessage(Message message) {
        // Example: Logging message after processing
        log.info("Postprocessing message: {}", message.getContent());
    }
}

