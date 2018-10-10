package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool2<T> {
	private int size;
	private Semaphore available;
	private List<T> list = new ArrayList<>();
	private volatile boolean[] checkOut;

	public Pool2(Class<T> classObject, int size) {
		this.size = size;
		available = new Semaphore(size, true);
		checkOut = new boolean[size];
		for (int i = 0; i < size; i++)
			try {
				list.add(classObject.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	}

	public void checkIn(T t) {
		if (releaseItem(t))
			available.release();
	}

	public T checkOut() {
		try {
			available.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getItem();
	}

	private synchronized T getItem() {
		for (int i = 0; i < size; i++)
			if (!checkOut[i]) {
				T t = list.get(i);
				checkOut[i] = true;
				return t;
			}
		return null;
	}

	private boolean releaseItem(T t) {
		int i = list.indexOf(t);
		if (i == -1)
			return false;
		if (checkOut[i]) {
			checkOut[i] = false;
			return true;
		}
		return false;
	}

}
