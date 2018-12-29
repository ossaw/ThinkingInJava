package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class BasicFileOutput2 {
    static String fileName = "D:\\Workspaces\\Eclipse\\thinking-In-Java-master\\src\\main\\java\\io\\BasicFileOutput2.java";
    static String fileOut = "D:\\Workspaces\\Eclipse\\thinking-In-Java-master\\src\\main\\java\\io\\BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(
                BufferedInputFile.read(fileName)));
        br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(new File(fileOut));
        String s;
        int lineNumber = 1;
        while ((s = br.readLine()) != null) {
            // System.out.println(s);
            // sb.append(s + "\n");
            pw.println(lineNumber++ + ": " + s);
        }
        pw.flush();
        pw.close();
        br.close();
        System.out.println(BufferedInputFile.read(fileOut));
    }
}
