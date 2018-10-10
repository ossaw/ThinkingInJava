package innerclasses;

interface Selector2 {
	boolean end();

	Object current();

	Object next();
}

public class Sequence2 {
	private Object[] elemengtData;
	private int next = 0;

	public Sequence2(int size) {
		elemengtData = new Object[size];
	}

	public void add(Object o) {
		if (next < elemengtData.length)
			elemengtData[next++] = o;
	}

	private class SequenceSelector2 implements Selector2 {
		private int size = 0;

		@Override
		public Object next() {
			if (size < elemengtData.length)
				return elemengtData[size++];
			return null;
		}

		@Override
		public boolean end() {
			return size == elemengtData.length;
		}

		@Override
		public Object current() {
			return elemengtData[size];
		}

	}

	Selector2 selector() {
		return new SequenceSelector2();
	}

	public static void main(String[] args) {
		int size = 10;
		Sequence2 s2 = new Sequence2(size);
		for (int i = 0; i < size; i++)
			s2.add(i);
		Selector2 selector2 = s2.selector();
		while (!selector2.end()) {
			System.out.print(selector2.next() + " ");
		}

	}
}
