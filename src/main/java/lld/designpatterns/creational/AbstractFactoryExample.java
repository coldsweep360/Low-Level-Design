package lld.designpatterns.creational;

/** Creates matching UI widgets without the caller knowing the operating system. */
public class AbstractFactoryExample {
    // A factory promises to create every product in one family.
    interface UiFactory { Button createButton(); CheckBox createCheckBox(); }
    interface Button { void draw(); }
    interface CheckBox { void draw(); }

    // Windows products always come from the Windows factory.
    static class WindowsFactory implements UiFactory {
        public Button createButton() { return () -> System.out.println("Windows button"); }
        public CheckBox createCheckBox() { return () -> System.out.println("Windows checkbox"); }
    }
    // Mac products always come from the Mac factory.
    static class MacFactory implements UiFactory {
        public Button createButton() { return () -> System.out.println("Mac button"); }
        public CheckBox createCheckBox() { return () -> System.out.println("Mac checkbox"); }
    }

    // This application depends on abstractions, not on Windows or Mac classes.
    static void paintScreen(UiFactory factory) {
        factory.createButton().draw();
        factory.createCheckBox().draw();
    }

    public static void main(String[] args) { paintScreen(new WindowsFactory()); }
}
