package generics;

public class LinkedStack3<T> {
	private static class Node<E> {
		E value;
		Node<E> next;

		Node() {}

		Node(E value, Node next) {
			this.value = value;
			this.next = next;
		}

		boolean end() {
			return value == null && next == null;
		}
	}

	private Node<T> top = new Node<>();

	void push(T t) {
		//		if(t == null)
		//			throw new NullPointerException("push arg is null");
		top = new Node<T>(t, top);
	}

	T pop() {
		T result = top.value;
		if (!top.end())
			top = top.next;
		System.out.println(top.value);
		return result;
	}

	public static void main(String[] args) {
		LinkedStack3<String> ls3 = new LinkedStack3<>();
		//		String test = "test stack pop and push";
		//		for(String s : test.split(" "))
		//			ls3.push(s);
		//
		//		for(String s = ls3.pop(); s != null; s = ls3.pop())
		//			System.out.println(s);
		//
		//		String string;
		//		while((string = ls3.pop()) != null)
		//			System.out.println(string);

		ls3.push("pp ss");
		ls3.push(null);

		String string;
		while ((string = ls3.pop()) != null)
			System.out.println(string);
	}

}
