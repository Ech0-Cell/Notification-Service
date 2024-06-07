package com.yourcompany.notificationservice;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class MockKafka {
    private Queue<NotificationRecord> messageQueue = new LinkedBlockingQueue<>();

    public void addMessage(NotificationRecord record) {
        messageQueue.add(record);
    }

    public List<NotificationRecord> pollMessages(String topic) {
        List<NotificationRecord> records = new ArrayList<>();
        while (!messageQueue.isEmpty()) {
            records.add(messageQueue.poll());
        }
        return records;
    }
}
