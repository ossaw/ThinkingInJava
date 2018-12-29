package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile2 {

    public static String read(String fileName) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String s;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            while ((s = br.readLine()) != null)
                sb.append(s + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(read(
                "D:\\Workspaces\\Eclipse\\thinking-In-Java-master\\src\\main\\java\\io\\BufferedInputFile2.java"));
    }
}
