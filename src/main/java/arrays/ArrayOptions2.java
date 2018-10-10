package arrays;

import java.util.Arrays;

public class ArrayOptions2 {

	public static void main(String[] args) {
		BerylliumSphere2[] a;
		BerylliumSphere2[] b = new BerylliumSphere2[5];
		BerylliumSphere2[] c = new BerylliumSphere2[5];
		for (int i = 0; i < c.length; i++)
			if (c[i] == null)
				c[i] = new BerylliumSphere2();
		BerylliumSphere2[] d = { new BerylliumSphere2(), new BerylliumSphere2(),
				new BerylliumSphere2() };
		a = d;

		System.out.println("a.length = " + a.length);
		System.out.println("array a = " + Arrays.toString(a));
		System.out.println("b.length = " + b.length);
		System.out.println("array b = " + Arrays.toString(b));
		System.out.println("c.length = " + c.length);
		System.out.println("array c = " + Arrays.toString(c));
		System.out.println("d.length = " + d.length);
		System.out.println("array d = " + Arrays.toString(d));

		int[] e;
		int[] f = new int[5];
		int[] g = new int[5];
		for (int i = 0; i < g.length; i++)
			g[i] = i;
		int[] h = { 1, 2, 3, 4, 5 };
		e = h;

		System.out.println("e.length = " + e.length);
		System.out.println("array e = " + Arrays.toString(e));
		System.out.println("f.length = " + f.length);
		System.out.println("array f = " + Arrays.toString(f));
		System.out.println("g.length = " + g.length);
		System.out.println("array g = " + Arrays.toString(g));
		System.out.println("h.length = " + h.length);
		System.out.println("array h = " + Arrays.toString(h));

		char[] i = { 'a', 'b' };
		System.out.println("array i = " + Arrays.toString(i));

		boolean[] j = new boolean[5];
		System.out.println("array j = " + Arrays.toString(j));

		double[] k = new double[5];
		System.out.println("array k = " + Arrays.toString(k));

		byte[] l = new byte[5];
		System.out.println("array l = " + Arrays.toString(l));
	}

}
