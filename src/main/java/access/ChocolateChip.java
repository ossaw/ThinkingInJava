// : access/ChocolateChip.java
package access; /* Added by Eclipse.py */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Can't use package-access member from another package.
import access.dessert.Cookie;

public class ChocolateChip extends Cookie {
	public ChocolateChip() {
		System.out.println("ChocolateChip constructor");
	}

	public void chomp() {
		bite(); // Can't access bite
	}

	public static void main(String[] args) {
		// ChocolateChip x = new ChocolateChip();
		// x.chomp();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date y = c.getTime();
		String year = format.format(y);
		System.out.println(year);
	}
}
/*
 * Output: Cookie constructor ChocolateChip constructor
 */// :~
