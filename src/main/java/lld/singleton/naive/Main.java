package lld.singleton.naive;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        NaiveSingleton first = NaiveSingleton.getInstance();
        NaiveSingleton second = new NaiveSingleton();
        System.out.println("factory instance == directly-created instance: " + (first == second));
    }
}
