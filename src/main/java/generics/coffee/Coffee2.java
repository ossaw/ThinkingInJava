package generics.coffee;

public class Coffee2 {
    private static int counter = 0;
    private final int id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
