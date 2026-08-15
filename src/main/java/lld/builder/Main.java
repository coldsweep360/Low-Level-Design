package lld.builder;

/** Demonstrates fluent construction and builder reuse. */
public final class Main {

    private Main() {
    }

    /**
     * Demonstrates the separation between a mutable builder and the immutable
     * values it builds.
     *
     * @param args command-line arguments; unused by this demonstration
     */
    public static void main(String[] args) {
        AddressBuilder builder = new AddressBuilder();

        Address address = builder
                .setHouseNo("138")
                .setRoadName("SS ROAD")
                .setAddressLine1("Sodepur")
                .setAddressLine2("Kolkata")
                .build();

        // Reusing the builder changes only its working state. 'address' still
        // contains house number 138 because build() already made a snapshot.
        builder.setHouseNo("999");
        System.out.println(address.getHouseNo()); // 138
    }
}
