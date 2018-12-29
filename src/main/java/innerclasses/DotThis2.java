package innerclasses;

public class DotThis2 {

    private class Inner {
        public DotThis2 outer() {
            return DotThis2.this;
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public void f() {
        System.out.println("DotThis2.f()");
    }

    public static void main(String[] args) {
        DotThis2 dt2 = new DotThis2();
        DotThis2.Inner i = dt2.inner();
        i.outer().f();
    }
}
