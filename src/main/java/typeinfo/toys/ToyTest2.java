package typeinfo.toys;

interface H {}

interface W {}

interface S {}

class Toy2 {
	public Toy2() {}

	public Toy2(int i) {}
}

class FancyToy2 extends Toy2 implements H, W, S {
	public FancyToy2() {}

	public FancyToy2(int i) {
		super(i);
	}
}

public class ToyTest2 {
	static void printInfo(Class<?> c) {
		System.out.println("Class name: " + c.getName());
		System.out.println("Class is interface? " + c.isInterface());
		System.out.println("Class canonical name: " + c.getCanonicalName());
		System.out.println();
	}

	public static void main(String[] args) {
		Class<?> c = null;
		try {
			c = Class.forName("typeinfo.toys.FancyToy2");
		} catch (ClassNotFoundException e) {
			System.err.println("FancyToy2 class path is error!");
			System.exit(1);
		}
		printInfo(c);
		for (Class<?> i : c.getInterfaces())
			printInfo(i);
		Class<?> superclass = c.getSuperclass();
		Object o = null;
		try {
			o = superclass.newInstance();
		} catch (InstantiationException e) {
			System.err.println("InstantiationException");
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.err.println("IllegalAccessException");
			System.exit(1);
		}
		printInfo(o.getClass());
	}

}
