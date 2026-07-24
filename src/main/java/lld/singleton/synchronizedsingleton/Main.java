package lld.singleton.synchronizedsingleton;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println(SynchronizedSingleton.getInstance() == SynchronizedSingleton.getInstance());
    }
}
