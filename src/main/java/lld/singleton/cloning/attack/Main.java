package lld.singleton.cloning.attack;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        CloneableAttackSingleton original = CloneableAttackSingleton.getInstance();
        CloneableAttackSingleton copy = original.clone();
        System.out.println("same instance after cloning: " + (original == copy));
    }
}
