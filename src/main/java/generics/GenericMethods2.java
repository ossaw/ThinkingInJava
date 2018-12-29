package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericMethods2 {

    private <T> void f(T t) {
        List<T> list = new ArrayList<>();
        list.add(t);
        System.out.println(list);
    }

    public static void main(String[] args) {
        GenericMethods2 gm2 = new GenericMethods2();
        gm2.f("");
        gm2.f(1);
        gm2.f(2.0F);
        gm2.f('a');
        gm2.f(gm2);
    }

}
