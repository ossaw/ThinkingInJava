package net.mindview.util;

public class CountingGenerator2 {

	public static class Boolean implements Generator<java.lang.Boolean> {
		private boolean flag = false;

		@Override
		public java.lang.Boolean next() {
			flag = !flag;
			return flag;
		}
	}

	public static class Character implements Generator<java.lang.Character> {
		int index = -1;
		char[] chars = ("abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

		@Override
		public java.lang.Character next() {
			index = (index + 1) % chars.length;
			return chars[index];
		}
	}

	public static class Byte implements Generator<java.lang.Byte> {
		private byte value = 0;

		@Override
		public java.lang.Byte next() {
			return value++;
		}
	}

	public static class Short implements Generator<java.lang.Short> {
		private short value = 0;

		@Override
		public java.lang.Short next() {
			return value++;
		}
	}

	public static class Integer implements Generator<java.lang.Integer> {
		private int value = 0;

		@Override
		public java.lang.Integer next() {
			return value++;
		}
	}

	public static class Long implements Generator<java.lang.Long> {
		private long value = 0L;

		@Override
		public java.lang.Long next() {
			return value++;
		}
	}

	public static class Float implements Generator<java.lang.Float> {
		private float value = 0F;

		@Override
		public java.lang.Float next() {
			return value++;
		}
	}

	public static class Double implements Generator<java.lang.Double> {
		private double value = 0d;

		@Override
		public java.lang.Double next() {
			return value++;
		}
	}

	public static class String implements Generator<java.lang.String> {
		private int length = 7;
		private Character cg = new Character();

		@Override
		public java.lang.String next() {
			char[] chars = new char[length];
			for (int i = 0; i < chars.length; i++)
				chars[i] = cg.next();

			return new java.lang.String(chars);
		}
	}

}
