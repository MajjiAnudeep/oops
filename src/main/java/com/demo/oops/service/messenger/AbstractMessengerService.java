package com.demo.oops.service.messenger;

import com.demo.oops.apimodel.messenger.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public abstract class AbstractMessengerService implements MessengerService {
    private final BlockingQueue<Message> messageQueue;
    private final List<Message> processedMessages = new ArrayList<>();
    private final ExecutorService executorService;

    @Override
    public synchronized void sendMessage(Message message) {
        messageQueue.offer(message);
    }

    @Override
    public synchronized void startProcessing() {
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                try {
                    while (!executorService.isShutdown()) {
                        Message message = messageQueue.poll(1, TimeUnit.SECONDS);
                        if (message != null) {
                            processMessage(message);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    private synchronized void processMessage(Message message) {
        try {
            preprocessMessage(message);
            Thread.sleep(1000); // Simulating delay
            processedMessages.add(message);
            postprocessMessage(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract void preprocessMessage(Message message);
    protected abstract void postprocessMessage(Message message);

    @Override
    public List<Message> getProcessedMessages() {
        return new ArrayList<>(processedMessages);
    }

    @Override
    public void shutdown() {
        executorService.shutdownNow();
    }
}
