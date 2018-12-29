package typeinfo;

class Building2 {}

class House2 extends Building2 {}

public class ClassCasts2 {
    public static void main(String[] args) {
        Building2 b = new House2();
        Class<House2> h = House2.class;
        House2 c = h.cast(b);
        c = (House2) b;
        System.out.println(c.getClass().getName());
    }
}
