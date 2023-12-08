package com.demo.oops.apimodel.messenger;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TextMessage implements Message {
    private static final AtomicInteger idGenerator = new AtomicInteger();
    private final int id;
    private final String content;
    private final long timestamp;

    private TextMessage(String content) {
        this.id = idGenerator.incrementAndGet();
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public static TextMessage of(String content) {
        return new TextMessage(content);
    }

    @Override
    public int compareTo(Message other) {
        return Long.compare(this.getTimestamp(), other.getTimestamp());
    }
}