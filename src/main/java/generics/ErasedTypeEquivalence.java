// : generics/ErasedTypeEquivalence.java
package generics; /* Added by Eclipse.py */

import java.util.*;

public class ErasedTypeEquivalence {
	public static void main(String[] args) {
		Class<?> c1 = new ArrayList<String>().getClass();
		Class<?> c2 = new ArrayList<Integer>().getClass();
		c2 = ArrayList.class;
		System.out.println(c1 == c2);
	}
}
/*
 * Output: true
 */// :~
