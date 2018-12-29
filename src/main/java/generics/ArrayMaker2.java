package generics;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayMaker2<T> {
    private Class<T> kind;

    public ArrayMaker2(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    public T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker2<String> arrayMaker2 = new ArrayMaker2<>(String.class);
        String[] instances = arrayMaker2.create(10);
        System.out.println(Arrays.toString(instances));
    }

}
