package net.mindview.util;

public class BasicGenerator2<T> implements Generator2<T> {
    private Class<T> type;

    private BasicGenerator2(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T> BasicGenerator2<T> create(Class<T> type) {
        return new BasicGenerator2<>(type);
    }

}
