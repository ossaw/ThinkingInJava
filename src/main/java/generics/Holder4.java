package generics;

public class Holder4<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static void main(String[] args) {
        Holder4<Integer> holder4 = new Holder4<>();
        holder4.set(1);
        System.out.println(holder4.t instanceof Integer);
        System.out.println(holder4.get());
    }

}
