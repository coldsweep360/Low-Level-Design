package lld.pizza;

import lld.pizza.base_pizza.BasePizza;
import lld.pizza.base_pizza.MargheritaBaseImpl;
import lld.pizza.topping_decorator.ExtraCheeseToppingImpl;
import lld.pizza.topping_decorator.MushroomToppingImpl;

public class Order {
    public static void main(String[] args) {
        BasePizza pizza = new MushroomToppingImpl(new MargheritaBaseImpl());
        pizza = new ExtraCheeseToppingImpl(pizza);
        System.out.println(pizza.cost());
    }
}
