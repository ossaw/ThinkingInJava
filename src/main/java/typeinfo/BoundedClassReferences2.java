package typeinfo;

public class BoundedClassReferences2 {
	
	public static void main(String[] args) {
		Class<? extends Number> numberClass = int.class;
		numberClass = double.class;
		numberClass = Integer.class;
		numberClass = Number.class;
	}

}
