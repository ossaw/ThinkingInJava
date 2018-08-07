package typeinfo;

import java.util.Random;

class Initable100 {
	static final int STATIC_FINAL1 = 47;
	static final int STATIC_FINAL2 = ClassInitialization2.random.nextInt(1000);
	static {
		System.out.println("init Initable100!");
	}
}

class Initable101 {
	static int staticInt = 47;

	static {
		System.out.println("init Initable101!");
	}
}

class Initable102 {
	static int staticInt = 74;
	static {
		System.out.println("init Initable102!");
	}
}

public class ClassInitialization2 {
	static Random random = new Random(47);

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> initable100 = Initable100.class;
		System.out.println("After Initable100 init ...");
		System.out.println(Initable100.STATIC_FINAL1);
		System.out.println(Initable100.STATIC_FINAL2);

		System.out.println(Initable101.staticInt);

		Class<?> initable102 = Class.forName("typeinfo.Initable102");
		System.out.println("After Initable102 init ...");
		System.out.println(Initable102.staticInt);
	}
}
