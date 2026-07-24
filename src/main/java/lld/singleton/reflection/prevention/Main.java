package lld.singleton.reflection.prevention;

import java.lang.reflect.Constructor;

public final class Main {
    private Main() { }

    public static void main(String[] args) throws ReflectiveOperationException {
        ReflectionSafeSingleton first = ReflectionSafeSingleton.getInstance();
        Constructor<ReflectionSafeSingleton> constructor =
                ReflectionSafeSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
            System.out.println("unexpected reflective construction");
        } catch (ReflectiveOperationException exception) {
            // Constructor exceptions are wrapped by reflection in InvocationTargetException.
            System.out.println("reflection blocked: " + exception.getCause().getMessage());
        }
        System.out.println("normal instance exists: " + (first == ReflectionSafeSingleton.getInstance()));
    }
}
