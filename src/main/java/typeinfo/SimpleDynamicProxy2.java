package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Interface3 {
    void doSomeThing();

    void doSomeThingElse(String arg);
}

class RealObject3 implements Interface3 {
    @Override
    public void doSomeThing() {
        System.out.println("do some thing ...");
    }

    @Override
    public void doSomeThingElse(String arg) {
        System.out.println("do some thing " + arg + " ...");
    }
}

class ProxyObject3 implements InvocationHandler {
    private Object proxied;

    public ProxyObject3(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println(proxy.getClass().getName());

        if (args != null)
            for (Object arg : args)
                System.out.println(arg);

        method.invoke(proxied, args);
        return null;
    }

}

public class SimpleDynamicProxy2 {
    static void consumer(Interface3 interface3) {
        interface3.doSomeThing();
        interface3.doSomeThingElse(" args ");
    }

    public static void main(String[] args) {
        // consumer(new RealObject3());
        Interface3 interface3 = (Interface3) Proxy.newProxyInstance(
                Interface3.class.getClassLoader(), new Class[] {
                        Interface3.class }, new ProxyObject3(
                                new RealObject3()));

        consumer(interface3);
    }

}
