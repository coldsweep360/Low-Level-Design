package lld.pizza.topping_decorator;

import lld.pizza.base_pizza.BasePizza;

public class ExtraCheeseToppingImpl extends ToppingDecorator{

    BasePizza basePizza;

    public ExtraCheeseToppingImpl(BasePizza basePizza){
        this.basePizza = basePizza;
    }

    @Override
    public int cost(){
        return basePizza.cost()+10;
    }
}
