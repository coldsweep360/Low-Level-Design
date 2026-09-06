package lld.designpatterns.behavioral;

/**
 * Fixes the recipe steps while subclasses supply the drink-specific steps.
 */
public class TemplateMethodExample {
    static abstract class HotDrink {
        // final prevents subclasses from accidentally changing the important order.
        final void prepare() {
            boilWater();
            brew();
            pour();
            addExtras();
        }

        private void boilWater() {
            System.out.println("Boil water");
        }

        abstract void brew();

        abstract void addExtras();

        private void pour() {
            System.out.println("Pour into cup");
        }
    }

    static class Tea extends HotDrink {
        void brew() {
            System.out.println("Steep tea");
        }

        void addExtras() {
            System.out.println("Add lemon");
        }
    }

    public static void main(String[] args) {
        new Tea().prepare();
    }
}
