package lld.abstractfactory.factory;

import lld.abstractfactory.products.button.Button;
import lld.abstractfactory.products.button.MacButton;
import lld.abstractfactory.products.checkbox.Checkbox;
import lld.abstractfactory.products.checkbox.MacCheckbox;
import lld.abstractfactory.products.textbox.MacTextBox;
import lld.abstractfactory.products.textbox.TextBox;

public class MacFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

    @Override
    public TextBox createTextBox() {
        return new MacTextBox();
    }
}