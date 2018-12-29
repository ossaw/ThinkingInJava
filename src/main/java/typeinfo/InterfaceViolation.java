// : typeinfo/InterfaceViolation.java
package typeinfo; /* Added by Eclipse.py */

// Sneaking around an interface.

import typeinfo.interfacea.*;

class B implements A {
    @Override
    public void f() {}

    public void g() {
        System.out.println("g()");
    }
}

public class InterfaceViolation {
    public static void main(String[] args) {
        A a = new B();
        a.f();
        // a.g(); // Compile error
        System.out.println(a.getClass().getName());
        if (a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
}
/*
 * Output: B
 */// :~
