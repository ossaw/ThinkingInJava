// : io/ChangeSystemOut.java
package io; /* Added by Eclipse.py */

// Turn System.out into a PrintWriter.

import java.io.*;

public class ChangeSystemOut {
    public static void main(String[] args) {
        // 第二个参数为自动执行flush方法
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello, world");
        out.flush();
        out.close();
    }
}
/*
 * Output: Hello, world
 */// :~
