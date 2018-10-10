package net.mindview.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile2 extends ArrayList<String> {
	private static final long serialVersionUID = 996316007660725506L;

	public TextFile2(String text) {
		this(text, "\n");
	}

	public TextFile2(String text, String spliter) {
		super(Arrays.asList(text.split(spliter)));
		if ("".equals(get(0)))
			remove(0);
	}

	public static String read(String fileName) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					fileName).getAbsoluteFile()));
			String s;
			try {
				while ((s = br.readLine()) != null)
					sb.append(s + "\n");
			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void write(String fileName, String text) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					fileName).getAbsoluteFile()));
			try {
				bw.write(text);
				bw.flush();
			} finally {
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					fileName).getAbsoluteFile()));
			try {
				for (String s : this)
					bw.write(s);
				bw.close();
			} finally {
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String fileString = read(
				"src/main/java/net/mindview/util/TextFile2.java");
		write("src/main/java/net/mindview/util/test3.txt", fileString);
		TextFile2 tf2 = new TextFile2(fileString);
		tf2.write("src/main/java/net/mindview/util/test4.txt");
		TextFile2 textFile2 = new TextFile2(fileString, "\\W+");
		TreeSet<String> set = new TreeSet<>(textFile2);
		System.out.println(set.headSet("a"));
	}

}
