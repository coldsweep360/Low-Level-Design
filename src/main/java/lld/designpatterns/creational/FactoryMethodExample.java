package lld.designpatterns.creational;

/**
 * Lets each delivery service choose the transport it needs.
 */
public class FactoryMethodExample {
    interface Transport {
        void deliver();
    }

    static class Truck implements Transport {
        public void deliver() {
            System.out.println("Delivered by road");
        }
    }

    static class Ship implements Transport {
        public void deliver() {
            System.out.println("Delivered by sea");
        }
    }

    // Shared work uses a product, but the factory method leaves its exact type open.
    abstract static class Logistics {
        abstract Transport createTransport();

        void planDelivery() {
            createTransport().deliver();
        }
    }

    static class RoadLogistics extends Logistics {
        Transport createTransport() {
            return new Truck();
        }
    }

    static class SeaLogistics extends Logistics {
        Transport createTransport() {
            return new Ship();
        }
    }

    public static void main(String[] args) {
        new SeaLogistics().planDelivery();
    }
}
