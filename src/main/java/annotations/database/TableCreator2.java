package annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator2 {

	public static void main(String[] args) throws ClassNotFoundException {
		if (args.length == 0) {
			System.out.println("please input a valid class name ...");
			System.exit(0);
		}
		for (String className : args) {
			Class<?> cl = Class.forName(className);
			DBTable2 dt2 = cl.getAnnotation(DBTable2.class);
			if (dt2 == null) {
				System.out.println("this class do not have DBTable2 annotation ...");
				continue;
			}
			String tableName = dt2.name();
			if (tableName == null || tableName.length() == 0)
				tableName = cl.getSimpleName().toUpperCase();
			Field[] fields = cl.getDeclaredFields();
			List<String> columnDefs = new ArrayList<>();
			for (Field field : fields) {
				String columnName = null;
				Annotation[] annos = field.getAnnotations();
				if (annos[0] == null) {
					System.out.println("this field do not have annotions ...");
					continue;
				}
				if (annos[0] instanceof SQLInteger2) {
					SQLInteger2 st2 = (SQLInteger2) annos[0];
					if (st2.name() == null || st2.name().length() == 0)
						columnName = field.getName().toUpperCase();
					else
						columnName = st2.name();
					columnDefs.add(columnName + " INT" + getContrains(st2.constraints()));
				}

				if (annos[0] instanceof SQLString2) {
					SQLString2 st2 = (SQLString2) annos[0];
					if (st2.name() == null || st2.name().length() == 0)
						columnName = field.getName().toUpperCase();
					else
						columnName = st2.name();
					columnDefs.add(columnName + " VARCHAR(" + st2.value() + ")"
							+ getContrains(st2.constraints()));
				}
			}
			StringBuilder sb = new StringBuilder("CREATE TABLE " + tableName + "(");
			for (String columnName : columnDefs) {
				sb.append("\n\t").append(columnName).append(",");
			}
			String result = sb.substring(0, sb.length() - 1) + "\n);";
			System.out.println(result);
		}

	}

	static String getContrains(Constraints2 cst2) {
		String result = "";
		if (!cst2.allowNull()) {
			result += " NOT NULL";
		}
		if (cst2.primaryKey()) {
			result += " PRIMARY KEY";
		}
		if (cst2.unique()) {
			result += " UNIQUE";
		}
		return result;
	}

}
