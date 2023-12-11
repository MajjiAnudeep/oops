package com.demo.oops.service.messenger.impl;

import com.demo.oops.apimodel.messenger.IMessage;
import com.demo.oops.apimodel.messenger.StartMessagingResponse;
import com.demo.oops.factory.messenger.UserFactory;
import com.demo.oops.service.messenger.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

import static com.demo.oops.utility.MessageUtils.createUser;

@Component
@Slf4j
public class UserMessageServiceImpl implements UserMessageService {

    @Override
    public StartMessagingResponse startMessaging(int users) {
        var messageQueue = new PriorityBlockingQueue<IMessage>();
        var executorService = Executors.newFixedThreadPool(3);
        var messengerService = new MessagingServiceImpl(messageQueue, executorService);
        var sentMessages = new ArrayList<IMessage>();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < users; i++) {
            threads.add(new Thread(UserFactory.createUser(messengerService, createUser(i), sentMessages)));
        }

        messengerService.startProcessing();

        for (var thread : threads) {
            thread.start();
        }

        try {
            for (var thread : threads) {
                thread.join();
            }
        } catch (InterruptedException ex) {
            log.error("Error while sending messages.", ex);
        } finally {
            messengerService.shutdown();
        }

        return new StartMessagingResponse(
                sentMessages.size() == messengerService.getProcessedMessages().size(),
                sentMessages.stream().map(IMessage::getContent).collect(Collectors.toList()),
                messengerService.getProcessedMessages().stream().map(IMessage::getContent).collect(Collectors.toList())
        );
    }
}
