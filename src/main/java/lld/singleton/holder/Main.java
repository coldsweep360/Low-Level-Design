package lld.singleton.holder;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println(InitializationOnDemandHolderSingleton.getInstance()
                == InitializationOnDemandHolderSingleton.getInstance());
    }
}
