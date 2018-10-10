package innerclasses;

public class DotNew2 {
	class Inner {}

	public static void main(String[] args) {
		DotNew2 dn2 = new DotNew2();
		DotNew2.Inner dn2i = dn2.new Inner();
		System.out.println(dn2i);
	}

}
