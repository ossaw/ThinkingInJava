package concurrency;

/**
 * EvenGenerator2为共享资源，EvenChecker2为线程执行者。
 * 此实例为多个EvenChecker2线程操作一个EvenGenerator2对象。
 */
public class EvenGenerator2 extends IntGenerator2 {
	private int val;

	@Override
	int next() {
		++val;
		++val;
		// System.out.println(val);
		return val;
	}

	public static void main(String[] args) {
		EvenChecker2.test(new EvenGenerator2());
	}

}
