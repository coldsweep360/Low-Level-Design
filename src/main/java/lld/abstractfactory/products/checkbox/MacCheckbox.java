package lld.abstractfactory.products.checkbox;

public class MacCheckbox implements Checkbox {

    @Override
    public void check() {
        System.out.println("Mac Checkbox");
    }
}