package lld.abstractfactory.products.textbox;

public class MacTextBox implements TextBox {

    @Override
    public void type() {
        System.out.println("Mac TextBox");
    }
}