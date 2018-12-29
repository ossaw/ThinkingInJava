package generics;

import generics.coffee.Latte2;
import net.mindview.util.BasicGenerator2;

public class BasicGeneratorDemo2 {
    public static void main(String[] args) {
        BasicGenerator2<Latte2> basicGenerator2 = BasicGenerator2.create(
                Latte2.class);
        for (int i = 0; i < 5; i++)
            System.out.println(basicGenerator2.next());
    }
}
