package typeinfo.toys;

public class GenericToyTest2 {
    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException {
        Class<FancyToy2> fClass = FancyToy2.class;
        Class<? super FancyToy2> sClass = fClass.getSuperclass();
        // compile error
        // Class<Toy2> tClass = fClass.getSuperclass();
        Object o = sClass.newInstance();
        System.out.println(o.getClass().getName());
    }
}
