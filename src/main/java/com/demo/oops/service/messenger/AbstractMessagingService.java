package com.demo.oops.service.messenger;

import com.demo.oops.apimodel.messenger.IMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class AbstractMessagingService implements MessagingService {
    private final BlockingQueue<IMessage> messageQueue;
    private final List<IMessage> processedMessages = new ArrayList<>();
    private final ExecutorService executorService;

    @Override
    public synchronized void sendMessage(IMessage message) {
        messageQueue.offer(message);
    }

    @Override
    public synchronized void startProcessing() {
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                try {
                    while (!executorService.isShutdown()) {
                        IMessage message = messageQueue.take();
                        processMessage(message);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    private synchronized void processMessage(IMessage message) {
        try {
            preprocessMessage(message);
            Thread.sleep(1000); // Simulating delay
            processedMessages.add(message);
            postprocessMessage(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract void preprocessMessage(IMessage message);
    protected abstract void postprocessMessage(IMessage message);

    @Override
    public List<IMessage> getProcessedMessages() {
        return new ArrayList<>(processedMessages);
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
        try {
            while (!executorService.isTerminated()) {
                log.info("Tasks still pending...");
                log.info("Await termination for 0.5s : {}", executorService.awaitTermination(500, TimeUnit.MILLISECONDS));
            }
        } catch (InterruptedException ex) {
            log.error("Service interrupted : ", ex);
            Thread.currentThread().interrupt();
        }
    }
}
