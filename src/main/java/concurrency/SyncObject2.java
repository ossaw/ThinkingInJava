package concurrency;

public class SyncObject2 {
	private Object syncObject = new Object();
	
	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}
	
	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
	
	// 测试同类中方法获取不同对象锁
	public static void main(String[] args) {
		SyncObject2 so2 = new SyncObject2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				so2.f();
			}
		}).start();
		
		so2.g();
	}

}
