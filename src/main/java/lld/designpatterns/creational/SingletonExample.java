package lld.designpatterns.creational;

/** A thread-safe, lazily created shared application settings object. */
public class SingletonExample {
    static class AppSettings {
        private AppSettings() { } // Private means callers cannot use new AppSettings().
        // Java loads this nested class only when getInstance first asks for INSTANCE.
        private static class Holder { private static final AppSettings INSTANCE = new AppSettings(); }
        static AppSettings getInstance() { return Holder.INSTANCE; }
        String currency() { return "INR"; }
    }
    public static void main(String[] args) {
        AppSettings first = AppSettings.getInstance(); AppSettings second = AppSettings.getInstance();
        System.out.println("Same object: " + (first == second) + ", currency: " + first.currency());
    }
}
