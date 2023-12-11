package com.demo.oops.service.messenger.impl;

import com.demo.oops.apimodel.messenger.IMessage;
import com.demo.oops.service.messenger.AbstractMessagingService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@Slf4j
public class MessagingServiceImpl extends AbstractMessagingService {

    public MessagingServiceImpl(BlockingQueue<IMessage> messageQueue, ExecutorService executorService) {
        super(messageQueue, executorService);
    }

    @Override
    protected void preprocessMessage(IMessage message) {
        // Example: Logging message before processing
        log.info("Preprocessing message: {}", message.getContent());
    }

    @Override
    protected void postprocessMessage(IMessage message) {
        // Example: Logging message after processing
        log.info("Postprocessing message: {}", message.getContent());
    }
}

