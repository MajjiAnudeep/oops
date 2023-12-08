package com.demo.oops.apimodel.messenger;

import com.demo.oops.constant.Constants;
import com.demo.oops.factory.messenger.MessageFactory;
import com.demo.oops.service.messenger.MessengerService;
import lombok.AllArgsConstructor;
import java.util.List;

public class User implements Runnable {
    private final MessengerService messengerService;
    private final String userName;
    private final List<Message> sentMessages;

    private User(MessengerService messengerService, String userName, List<Message> sentMessages) {
        this.messengerService = messengerService;
        this.userName = userName;
        this.sentMessages = sentMessages;
    }

    public static User of(MessengerService messengerService, String userName, List<Message> sentMessages) {
        return new User(messengerService, userName, sentMessages);
    }

    @Override
    public void run() {
        for (var i = 0; i < 5; i++) {
            var message = MessageFactory.createMessage(
                    MessageFactory.MessageType.TEXT, String.format(Constants.SAMPLE_MESSAGE, userName, (i + 1)));
            synchronized (sentMessages) {
                sentMessages.add(message);
            }
            messengerService.sendMessage(message);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
