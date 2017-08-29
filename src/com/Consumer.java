package com;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

	private final Queue sharedQueue;

	public Consumer(Queue sharedQueue) {
		super();
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (sharedQueue) {

				while (sharedQueue.size() == 0) {
					System.out.println("Queue is empty, waiting");
					try {
						sharedQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()
						+ " consumes: " + sharedQueue.poll());
				sharedQueue.notify();
			}
		}
	}
}
