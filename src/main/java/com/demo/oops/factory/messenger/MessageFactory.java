package com.demo.oops.factory.messenger;

import com.demo.oops.apimodel.messenger.IMessage;
import com.demo.oops.apimodel.messenger.TextMessage;
import com.demo.oops.constant.MessageType;

public class MessageFactory {
    public static IMessage createMessage(MessageType type, String content) {
        switch (type) {
            case TEXT:
                return TextMessage.of(content);
            case MULTIMEDIA:
            case GROUP:
            default:
                throw new IllegalArgumentException("Type not supported yet: " + type);
        }
    }
}
