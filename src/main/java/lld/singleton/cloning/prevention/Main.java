package lld.singleton.cloning.prevention;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        CloneSafeSingleton first = CloneSafeSingleton.getInstance();
        System.out.println("canonical instance is stable: " + (first == CloneSafeSingleton.getInstance()));
        System.out.println("cloning is intentionally unavailable through this API");
    }
}
