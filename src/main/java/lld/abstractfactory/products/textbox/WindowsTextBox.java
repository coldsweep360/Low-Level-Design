package lld.abstractfactory.products.textbox;

public class WindowsTextBox implements TextBox {

    @Override
    public void type() {
        System.out.println("Windows TextBox");
    }
}