package lld.singleton.staticblock;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println(StaticBlockSingleton.getInstance() == StaticBlockSingleton.getInstance());
    }
}
