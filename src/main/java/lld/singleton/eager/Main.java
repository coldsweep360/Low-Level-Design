package lld.singleton.eager;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println(EagerSingleton.getInstance() == EagerSingleton.getInstance());
    }
}
