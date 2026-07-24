package lld.abstractfactory.client;

import lld.abstractfactory.factory.GUIFactory;
import lld.abstractfactory.factory.GUIFactoryProducer;
import lld.abstractfactory.products.button.Button;
import lld.abstractfactory.products.checkbox.Checkbox;
import lld.abstractfactory.products.textbox.TextBox;

public class Client {

    public static void main(String[] args) {

        GUIFactory factory = GUIFactoryProducer.getFactory("windows");

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        TextBox textBox = factory.createTextBox();

        button.paint();
        checkbox.check();
        textBox.type();

    }
}