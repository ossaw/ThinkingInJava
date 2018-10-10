package innerclasses;

import innerclasses.Parcel4.PDestination;

public class Parcel20 {

	public Destination destination(String s) {
		class PDestination implements Destination {
			private String string;

			private PDestination(String arg) {
				string = arg;
			}

			@Override
			public String readLabel() {
				return string;
			}
		}
		return new PDestination(s);
	}

	public static void main(String[] args) {
		Parcel20 p20 = new Parcel20();
		Destination destination = p20.destination("test");
		System.out.println(destination.readLabel());
	}

}
