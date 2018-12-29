package typeinfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import typeinfo.interfacea.A2;
import typeinfo.packageaccess.HiddenC2;

public class HiddenImplementation2 {
    public static void main(String[] args) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        A2 a2 = HiddenC2.makeA2();
        a2.f();
        System.out.println(a2.getClass().getName());

        callHiddenMethod(a2, "g");
        callHiddenMethod(a2, "h");
        callHiddenMethod(a2, "i");
        callHiddenMethod(a2, "j");
    }

    static void callHiddenMethod(Object o, String methodName)
            throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        Method method = o.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(o);
    }

}
