//: holding/EnvironmentVariables.java
package holding; /* Added by Eclipse.py */

import java.util.*;

public class EnvironmentVariables {
	public static void main(String[] args) {
		for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
/* (Execute to see output) */// :~
