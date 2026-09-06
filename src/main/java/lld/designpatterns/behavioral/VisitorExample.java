package lld.designpatterns.behavioral;

/** Adds tax calculation to product types without placing tax code inside the products. */
public class VisitorExample {
    interface Product { int accept(TaxVisitor visitor); }
    static class Book implements Product { public int accept(TaxVisitor visitor) { return visitor.visit(this); } }
    static class Food implements Product { public int accept(TaxVisitor visitor) { return visitor.visit(this); } }
    interface TaxVisitor { int visit(Book book); int visit(Food food); }
    static class IndiaTax implements TaxVisitor { public int visit(Book book) { return 5; } public int visit(Food food) { return 0; } }
    public static void main(String[] args) { TaxVisitor tax = new IndiaTax(); System.out.println("Book tax: " + new Book().accept(tax)); System.out.println("Food tax: " + new Food().accept(tax)); }
}
