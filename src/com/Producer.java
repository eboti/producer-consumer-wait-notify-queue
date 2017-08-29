package com;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

	private final Queue sharedQueue;

	private int QUEUE_SIZE = 3;

	public Producer(Queue sharedQueue) {
		super();
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {
			synchronized (sharedQueue) {

				while (sharedQueue.size() >= QUEUE_SIZE) {
					System.out.println("Queue is full, waiting");
					try {
						sharedQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println(Thread.currentThread().getName()
						+ " produce: " + i);
				sharedQueue.add(i);
				sharedQueue.notify();
			}
		}
	}
}
