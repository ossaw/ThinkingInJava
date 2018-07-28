package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList4 {
	
	public static void main(String[] args) {
		File file = new File(".");
		String [] list;
		if (args.length == 0)
			list = file.list();
		else	
			list = file.list(new FileNameFilter(args[0]));
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String f : list)
			System.out.println(f);
	}

}

class FileNameFilter implements FilenameFilter {
	private Pattern pattern;
	
	public FileNameFilter(String regex) {
		pattern = Pattern.compile(regex);
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
	
}