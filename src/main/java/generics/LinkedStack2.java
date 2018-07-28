package generics;

public class LinkedStack2<T> {
	private static class Node<E> {
		E e;
		Node<E> next;

		Node() {
			e = null;
			next = null;
		}

		Node(E e, Node<E> next) {
			this.e = e;
			this.next = next;
		}

		boolean end() {
			return e == null && next == null;
		}
	}

	private Node<T> top = new Node<>();

	void push(T t) {
		top = new Node<T>(t, top);
	}

	T pop() {
		T result = top.e;
		if (!top.end())
			top = top.next;
		return result;
	}
	
	public static void main(String[] args) {
		LinkedStack2<String> linkedStack2 = new LinkedStack2<>();
		String testString = "test stack pop push";
		for (String string : testString.split(" "))
			linkedStack2.push(string);
		String s;
		while ((s = linkedStack2.pop()) != null)
			System.out.println(s);
		
		System.out.println(s);
	}

}
