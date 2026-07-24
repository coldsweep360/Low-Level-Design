package lld.singleton.reflection.attack;

import java.lang.reflect.Constructor;

public final class Main {
    private Main() { }

    public static void main(String[] args) throws ReflectiveOperationException {
        ReflectionAttackSingleton first = ReflectionAttackSingleton.getInstance();
        Constructor<ReflectionAttackSingleton> constructor =
                ReflectionAttackSingleton.class.getDeclaredConstructor();
        // This suppresses Java language access checks for this user-defined class.
        constructor.setAccessible(true);
        ReflectionAttackSingleton second = constructor.newInstance();
        System.out.println("same instance: " + (first == second));
    }
}
