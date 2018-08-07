package typeinfo;

import static net.mindview.util.Print.print;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import sun.util.logging.resources.logging_zh_TW;

public class ShowMethods2 {
	private static final String USAGE = "peplease input a parameter of class path";
	private static Pattern p = Pattern.compile("\\w+\\.");

	public static void main(String[] args) {
		if (args.length == 0) {
			print(USAGE);
			System.exit(1);
		}
		int line = 0;
		try {
			Class<?> c = Class.forName(args[0]);
			Constructor<?>[] ctcs = c.getConstructors();
			Method[] metheds = c.getMethods();
			if (args.length == 1) {
				for (Method method : metheds)
					print(p.matcher(method.toString()).replaceAll(""));
				print();
				for (Constructor<?> ctc : ctcs)
					print(p.matcher(ctc.toString()).replaceAll(""));
				line = metheds.length + ctcs.length;
			} else if (args.length == 2) {
				for (Method method : metheds)
					if (metheds.toString().indexOf(args[1]) != -1) {
						print(p.matcher(method.toString()).replaceAll(""));
						line++;
					}
					print();
				for (Constructor<?> ctc : ctcs)
					if (ctc.toString().indexOf(args[1]) != -1) {
						print(p.matcher(ctc.toString()).replaceAll(""));
						line++;
					}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		print(line);
	}
}
