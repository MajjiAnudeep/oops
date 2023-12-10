package com.demo.oops.service.messenger.impl;

import com.demo.oops.apimodel.messenger.Message;
import com.demo.oops.constant.Constants;
import com.demo.oops.factory.messenger.UserFactory;
import com.demo.oops.service.messenger.MessagingSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@Component
@Slf4j
public class MessagingSystemImpl implements MessagingSystem {

    @Override
    public void startMessaging(int users) {
        var messageQueue = new PriorityBlockingQueue<Message>();
        var executorService = Executors.newFixedThreadPool(3);
        var messengerService = new MessengerServiceImpl(messageQueue, executorService);
        var sentMessages = new ArrayList<Message>();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < users; i++) {
            threads.add(new Thread(UserFactory.createUser(messengerService,
                    String.format(Constants.SAMPLE_USER, (i + 1)),
                    sentMessages)));
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
    }
}
