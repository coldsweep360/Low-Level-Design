package lld.singleton.enumsingleton;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println(EnumSingleton.INSTANCE == EnumSingleton.INSTANCE);
        System.out.println(EnumSingleton.INSTANCE.description());
    }
}
