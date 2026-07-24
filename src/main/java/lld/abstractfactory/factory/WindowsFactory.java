package lld.abstractfactory.factory;

import lld.abstractfactory.products.button.Button;
import lld.abstractfactory.products.button.WindowsButton;
import lld.abstractfactory.products.checkbox.Checkbox;
import lld.abstractfactory.products.checkbox.WindowsCheckbox;
import lld.abstractfactory.products.textbox.TextBox;
import lld.abstractfactory.products.textbox.WindowsTextBox;

public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

    @Override
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }
}