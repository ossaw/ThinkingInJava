// : typeinfo/SimpleProxyDemo.java
package typeinfo; /* Added by Eclipse.py */

import static net.mindview.util.Print.*;

interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject implements Interface {
    public void doSomething() {
        print("doSomething");
    }

    public void somethingElse(String arg) {
        print("somethingElse " + arg);
    }
}

class ProxyObject2 implements Interface2 {
    private Interface2 interface2;

    public ProxyObject2(Interface2 interface2) {
        this.interface2 = interface2;
    }

    @Override
    public void doSomeThing() {
        System.out.println("before do some thing ...");
        interface2.doSomeThing();
    }

    @Override
    public void doSomeThingElse(String arg) {
        System.out.println("before do some thing ...");
        interface2.doSomeThingElse(arg);
    }

}

class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    public void doSomething() {
        print("SimpleProxy doSomething");
        proxied.doSomething();
    }

    public void somethingElse(String arg) {
        print("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
    }
}

class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
/*
 * Output: doSomething somethingElse bonobo SimpleProxy doSomething doSomething
 * SimpleProxy somethingElse bonobo somethingElse bonobo
 */// :~
