package annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker2 {

	static void trackUseCases(List<Integer> list, Class<?> type) {
		for (Method m : type.getDeclaredMethods()) {
			UseCase2 useCase2 = m.getAnnotation(UseCase2.class);
			if (useCase2 != null) {
				System.out.println("id = " + useCase2.id() + " and desc = "
						+ useCase2.desc());
				// useCase2.id()需要初始化为Integer
				list.remove(new Integer(useCase2.id()));
			}
		}
		for (Integer i : list)
			System.out.println(i + " is not exist!");
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Collections.addAll(list, 45, 46, 47, 48);
		trackUseCases(list, PasswordUtils2.class);
	}

}
