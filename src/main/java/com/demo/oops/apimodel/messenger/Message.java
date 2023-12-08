package com.demo.oops.apimodel.messenger;

public interface Message extends Comparable<Message> {
    int getId();
    String getContent();
    long getTimestamp();

    @Override
    default int compareTo(Message other) {
        return Long.compare(this.getTimestamp(), other.getTimestamp());
    }
}
