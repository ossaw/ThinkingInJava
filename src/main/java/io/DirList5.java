package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList5 {
	private static FilenameFilter filter(final String regex) {
		return new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);

			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		};
	}

	public static void main(String[] args) {
		File file = new File(".");
		String[] fileList;
		if (args.length == 0)
			fileList = file.list();
		else
			fileList = file.list(filter(args[0]));
		Arrays.sort(fileList, String.CASE_INSENSITIVE_ORDER);
		for (String f : fileList)
			System.out.println(f);
	}
}
