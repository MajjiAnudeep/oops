package com.demo.oops.factory.messenger;

import com.demo.oops.apimodel.messenger.Message;
import com.demo.oops.apimodel.messenger.User;
import com.demo.oops.service.messenger.MessengerService;

import java.util.List;

public class UserFactory {
    public static User createUser(MessengerService messengerService, String userName, List<Message> sentMessages) {
        return User.of(messengerService, userName, sentMessages);
    }
}
