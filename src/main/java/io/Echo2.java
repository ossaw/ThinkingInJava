package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Echo2 {
	public static void main(String[] args) throws IOException {
		// 按字节输入
		BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in));
		String s;
		try {
			while ((s = br.readLine()) != null && s.length() != 0)
				System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
	}
}
