// : io/BasicFileOutput.java
package io; /* Added by Eclipse.py */

import java.io.*;

public class BasicFileOutput {
	static String file = "D:\\Workspaces\\Eclipse\\thinking-In-Java-master\\src\\main\\java\\io\\BasicFileOutput.java";

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new StringReader(
				BufferedInputFile.read(file)));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				file)));
		int lineCount = 1;
		String s;
		while ((s = in.readLine()) != null)
			out.println(lineCount++ + ": " + s);
		out.flush();
		out.close();
		// Show the stored file:
		System.out.println(BufferedInputFile.read(file));
	}
}
/* (Execute to see output) */// :~
