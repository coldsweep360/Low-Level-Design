package lld.pizza.topping_decorator;

import lld.pizza.base_pizza.BasePizza;

public class MushroomToppingImpl extends ToppingDecorator{

    BasePizza basePizza;

    public MushroomToppingImpl(BasePizza basePizza){
        this.basePizza = basePizza;
    }

    @Override
    public int cost(){
        return basePizza.cost()+20;
    }
}
