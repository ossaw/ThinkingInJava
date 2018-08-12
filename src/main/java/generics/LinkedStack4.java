package generics;

public class LinkedStack4<T> {
	private class Node<E> {
		private E item;
		private Node<E> next;

		public Node() {
			item = null;
			next = null;
		}

		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}

		boolean end() {
			return item == null && next == null;
		}
	}

	private Node<T> top = new Node<T>();

	public void push(T t) {
		if (t == null)
			throw new NullPointerException("T can not null");
		top = new Node<T>(t, top);
	}

	public T pop() {
		T result = top.item;
		if (!top.end())
			top = top.next;
		return result;
	}

	public static void main(String[] args) {
		String test = "LinkedStack test generic";
		LinkedStack4<String> ls4 = new LinkedStack4<>();
		for (String s : test.split(" "))
			ls4.push(s);
		
		String s;
		while ((s = ls4.pop()) != null)
			System.out.println(s);
	}
}
