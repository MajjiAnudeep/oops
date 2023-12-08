package com.demo.oops.factory.messenger;

import com.demo.oops.apimodel.messenger.Message;
import com.demo.oops.apimodel.messenger.TextMessage;

public class MessageFactory {
    public static Message createMessage(MessageType type, String content) {
        switch (type) {
            case TEXT:
                return TextMessage.of(content);
            case MULTIMEDIA:
            case GROUP:
            default:
                throw new IllegalArgumentException("Type not supported yet: " + type);
        }
    }

    public enum MessageType {
        TEXT,
        MULTIMEDIA,
        GROUP;
    }
}
