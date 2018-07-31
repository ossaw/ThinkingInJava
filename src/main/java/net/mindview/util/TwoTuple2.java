package net.mindview.util;

public class TwoTuple2<K, V> {
	private K k;
	private V v;
	
	public TwoTuple2(K k, V v) {
		this.k = k;
		this.v = v;
	}
	
	public TwoTuple2() {
	}
	
	@Override
	public String toString() {
		return "{ " + k + " : " + v + " }";
	}
	
	public static void main(String[] args) {
		TwoTuple2<Integer, String> tt = new TwoTuple2<Integer, String>(2, "4");
		
		System.out.println(tt);
	}
}
