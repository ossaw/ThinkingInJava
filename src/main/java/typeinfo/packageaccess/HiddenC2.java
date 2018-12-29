package typeinfo.packageaccess;

import static net.mindview.util.Print.print;

import typeinfo.interfacea.A2;

class C2 implements A2 {
    @Override
    public void f() {
        print("HiddenC2 f() ...");
    }

    public void g() {
        print("HiddenC2 g() ...");
    }

    void h() {
        print("Hidden h() ...");
    }

    protected void i() {
        print("HiddenC2 i() ...");
    }

    private void j() {
        print("HiddenC2 j() ...");
    }
}

public class HiddenC2 {
    public static A2 makeA2() {
        return new C2();
    }
}