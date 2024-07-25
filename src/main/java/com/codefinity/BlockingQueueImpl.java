package com.codefinity;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueImpl {
    private final List<Object> queue = new LinkedList();
    private int limit = 10;

    public BlockingQueueImpl() {
    }

    public BlockingQueueImpl(int limit){
        this.limit = limit;
    }

    public synchronized void put(Object item) throws InterruptedException  {
        while (queue.size() == limit) {
            wait();
        }
        if (queue.isEmpty()) {
            notifyAll();
        }
        queue.add(item);
    }


    public synchronized Object take() throws InterruptedException {
        while (queue.isEmpty()){
            wait();
        }
        if (queue.size() == limit){
            notifyAll();
        }

        return queue.remove(0);
    }

    public Integer size() {
        return queue.size();
    }

    public List<Object> getQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return "BlockingQueueImpl{" +
                "queue=" + queue +
                ", limit=" + limit +
                '}';
    }
}
