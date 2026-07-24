package lld.singleton.doublecheckedlocking;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println(DoubleCheckedLockingSingleton.getInstance()
                == DoubleCheckedLockingSingleton.getInstance());
    }
}
