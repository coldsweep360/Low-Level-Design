package lld.abstractfactory.products.button;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("Windows Button");
    }
}