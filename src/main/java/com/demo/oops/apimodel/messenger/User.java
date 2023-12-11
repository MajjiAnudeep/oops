package com.demo.oops.apimodel.messenger;

import com.demo.oops.constant.MessageType;
import com.demo.oops.factory.messenger.MessageFactory;
import com.demo.oops.service.messenger.MessagingService;

import java.util.List;

import static com.demo.oops.utility.MessageUtils.*;


public class User implements Runnable {
    private final MessagingService messagingService;
    private final String userName;
    private final List<IMessage> sentMessages;
    private final int messagesPerUser;

    private User(MessagingService messagingService, String userName, List<IMessage> sentMessages, int messagesPerUser) {
        this.messagingService = messagingService;
        this.userName = userName;
        this.sentMessages = sentMessages;
        this.messagesPerUser = messagesPerUser;
    }

    public static User of(MessagingService messagingService, String userName, List<IMessage> sentMessages, int messagesPerUser) {
        return new User(messagingService, userName, sentMessages, messagesPerUser);
    }

    @Override
    public void run() {
        for (var i = 0; i < messagesPerUser; i++) {
            var message = MessageFactory.createMessage(
                    MessageType.TEXT, createContent(userName, i));
            synchronized (sentMessages) {
                sentMessages.add(message);
            }
            messagingService.sendMessage(message);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
