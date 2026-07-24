package lld.abstractfactory.factory;

public class GUIFactoryProducer {

    public static GUIFactory getFactory(String os) {

        if ("windows".equalsIgnoreCase(os)) {
            return new WindowsFactory();
        }

        if ("mac".equalsIgnoreCase(os)) {
            return new MacFactory();
        }

        throw new IllegalArgumentException("Unsupported OS : " + os);
    }
}