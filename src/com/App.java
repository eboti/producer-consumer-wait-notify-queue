package com;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

//no need wait/notify, since it's automatic because of the blocking queue
public class App {

	public static <E> void main(String[] args) {

		Queue sharedQueue = new LinkedList();

		Thread producerThread = new Producer(sharedQueue);
		Thread consumerThread = new Consumer(sharedQueue);

		// Thread producerThread = new Thread(new Producer(sharedQueue),
		// Producer.class.getSimpleName());
		// Thread consumerThread = new Thread(new Consumer(sharedQueue),
		// Consumer.class.getSimpleName());

		producerThread.start();
		consumerThread.start();
	}

}
