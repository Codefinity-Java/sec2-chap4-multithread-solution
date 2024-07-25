package com.codefinity;


import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueImpl blockingQueue = new BlockingQueueImpl(); //default limit - 10

        Thread producer= new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    blockingQueue.put(i);
                    System.out.println("Producer: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(5000);
                for (int i = 0; i < 100; i++) {
                    blockingQueue.take();
                    System.out.println("Consumer: " + i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("END: " + blockingQueue.getQueue());

    }
}