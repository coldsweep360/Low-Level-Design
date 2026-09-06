package lld.designpatterns.structural;

/** Adds pizza toppings by wrapping a base pizza, without changing the base class. */
public class DecoratorExample {
    interface Pizza { String description(); int cost(); }
    static class PlainPizza implements Pizza { public String description() { return "Plain pizza"; } public int cost() { return 200; } }
    // Every topping is also a Pizza, so wrappers can be stacked in any order.
    abstract static class Topping implements Pizza { final Pizza base; Topping(Pizza base) { this.base = base; } }
    static class Cheese extends Topping { Cheese(Pizza base) { super(base); } public String description() { return base.description() + "+ cheese"; } public int cost() { return base.cost() + 50; } }
    static class Olives extends Topping { Olives(Pizza base) { super(base); } public String description() { return base.description() + "+ olives"; } public int cost() { return base.cost() + 30; } }
    public static void main(String[] args) { Pizza order = new Olives(new Cheese(new PlainPizza())); System.out.println(order.description() + ": Rs " + order.cost()); }
}
