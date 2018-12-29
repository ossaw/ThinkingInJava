// : io/TestEOF.java
package io; /* Added by Eclipse.py */

// Testing for end of file while reading a byte at a time.

import java.io.*;

public class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream(
                        "D:\\Workspaces\\Eclipse\\thinking-In-Java-master\\src\\main\\java\\io\\TestEOF.java")));
        while (in.available() != 0)
            System.out.print((char) in.readByte());
        in.close();
    }
} /* (Execute to see output) */// :~
