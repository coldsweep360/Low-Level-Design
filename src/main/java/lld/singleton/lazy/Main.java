package lld.singleton.lazy;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println(LazySingleton.getInstance() == LazySingleton.getInstance());
    }
}
