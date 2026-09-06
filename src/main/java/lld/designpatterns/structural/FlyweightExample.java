package lld.designpatterns.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * Shares a tree's species data while each drawn tree keeps only its own position.
 */
public class FlyweightExample {
    // This immutable, shareable data is expensive in a real map application.
    record TreeType(String name, String color) {
        void draw(int x, int y) {
            System.out.println(color + " " + name + " at " + x + "," + y);
        }
    }

    static class TreeFactory {
        private final Map<String, TreeType> cache = new HashMap<>();

        TreeType get(String name, String color) {
            return cache.computeIfAbsent(name + color, ignored -> new TreeType(name, color));
        }
    }

    public static void main(String[] args) {
        TreeFactory factory = new TreeFactory();
        TreeType mango1 = factory.get("Mango", "green");
        TreeType mango2 = factory.get("Mango", "green");
        mango1.draw(1, 2);
        System.out.println("Shared type: " + (mango1 == mango2));
    }
}
