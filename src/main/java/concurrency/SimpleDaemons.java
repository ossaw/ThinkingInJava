//: concurrency/SimpleDaemons.java
package concurrency; /* Added by Eclipse.py */

// Daemon threads don't prevent the program from ending.

import java.util.concurrent.*;
import static net.mindview.util.Print.*;

/**
 * 程序中非后台线程全部结束之后，程序会终止，并杀死全部后台线程，main线程属于非后台线程。
 * 设置线程为后台线程，他不会左右程序是否停止。
 */
public class SimpleDaemons implements Runnable {
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				print(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			print("sleep() interrupted");
		}
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		print("All daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
/*
 * Output: (Sample) All daemons started Thread[Thread-0,5,main]
 * SimpleDaemons@530daa Thread[Thread-1,5,main] SimpleDaemons@a62fc3
 * Thread[Thread-2,5,main] SimpleDaemons@89ae9e Thread[Thread-3,5,main]
 * SimpleDaemons@1270b73 Thread[Thread-4,5,main] SimpleDaemons@60aeb0
 * Thread[Thread-5,5,main] SimpleDaemons@16caf43 Thread[Thread-6,5,main]
 * SimpleDaemons@66848c Thread[Thread-7,5,main] SimpleDaemons@8813f2
 * Thread[Thread-8,5,main] SimpleDaemons@1d58aae Thread[Thread-9,5,main]
 * SimpleDaemons@83cc67 ...
 */// :~
