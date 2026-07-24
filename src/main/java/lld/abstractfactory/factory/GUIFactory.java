package lld.abstractfactory.factory;

import lld.abstractfactory.products.button.Button;
import lld.abstractfactory.products.checkbox.Checkbox;
import lld.abstractfactory.products.textbox.TextBox;

public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

    TextBox createTextBox();

}