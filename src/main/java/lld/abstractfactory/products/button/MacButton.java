package lld.abstractfactory.products.button;

public class MacButton implements Button {

    @Override
    public void paint() {
        System.out.println("Mac Button");
    }
}