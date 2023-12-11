package com.demo.oops.apimodel.messenger;

public interface IMessage extends Comparable<IMessage> {
    int getId();
    String getContent();
    long getTimestamp();

    @Override
    default int compareTo(IMessage other) {
        return Long.compare(this.getTimestamp(), other.getTimestamp());
    }
}
