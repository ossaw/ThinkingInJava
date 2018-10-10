package typeinfo;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

interface Interface2 {
	void doSomeThing();

	void doSomeThingElse(String arg);
}

class RealObject2 implements Interface2 {
	@Override
	public void doSomeThing() {
		System.out.println("do some thing");
	}

	@Override
	public void doSomeThingElse(String arg) {
		System.out.println("do some thing else " + arg);
	}
}

public class SimpleProxyDemo2 {
	static void consumer(Interface2 interface2) {
		interface2.doSomeThing();
		interface2.doSomeThingElse(" args ");
	}

	public static void main(String[] args) {
		consumer(new RealObject2());
		consumer(new ProxyObject2(new RealObject2()));
	}

}
