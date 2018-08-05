package arrays;

import net.mindview.util.CountingGenerator2;
import net.mindview.util.Generator;

public class GeneratorsTest2 {
	
	private static void test(Class<?> argClass) {
		for (Class<?> type : argClass.getClasses()) {
			try {
				System.out.print(type.getSimpleName() + ": ");
				Generator<?> generator = (Generator<?>) type.newInstance();
				for (int i = 0; i < 10; i++)
					System.out.print(generator.next() + " ");
				System.out.println();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		test(CountingGenerator2.class);
		
	}

}
