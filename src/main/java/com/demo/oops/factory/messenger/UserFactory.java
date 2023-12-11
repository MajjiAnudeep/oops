package com.demo.oops.factory.messenger;

import com.demo.oops.apimodel.messenger.IMessage;
import com.demo.oops.apimodel.messenger.User;
import com.demo.oops.service.messenger.MessagingService;

import java.util.List;

public class UserFactory {
    public static User createUser(MessagingService messagingService, String userName, List<IMessage> sentMessages) {
        return User.of(messagingService, userName, sentMessages);
    }
}
