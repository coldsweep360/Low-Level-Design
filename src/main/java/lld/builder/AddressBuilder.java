package lld.builder;

/**
 * Mutable builder used to assemble an {@link Address} one field at a time.
 *
 * <p>Each {@code set...} method returns {@code this}: the same builder
 * instance. That return value enables fluent chaining such as
 * {@code new AddressBuilder().setHouseNo("138").setRoadName("SS Road")}.</p>
 *
 * <p>A builder may be reused. Every {@link #build()} call creates a new,
 * independent {@code Address} snapshot from the builder's current state.</p>
 */
public final class AddressBuilder {
    private String houseNo;
    private String roadName;
    private String addressLine1;
    private String addressLine2;

    /**
     * Sets the optional house or flat number.
     *
     * @param houseNo house or flat number
     * @return this builder, allowing another builder method to follow
     */
    public AddressBuilder setHouseNo(String houseNo) {
        this.houseNo = houseNo;
        return this;
    }

    /**
     * Sets the required road name.
     *
     * @param roadName road name; it must contain non-whitespace text at build time
     * @return this builder, allowing another builder method to follow
     */
    public AddressBuilder setRoadName(String roadName) {
        this.roadName = roadName;
        return this;
    }

    /**
     * Sets the first optional address line.
     *
     * @param addressLine1 first address line
     * @return this builder, allowing another builder method to follow
     */
    public AddressBuilder setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    /**
     * Sets the second optional address line.
     *
     * @param addressLine2 second address line
     * @return this builder, allowing another builder method to follow
     */
    public AddressBuilder setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    /**
     * Validates the required state and creates an immutable address snapshot.
     *
     * @return a new immutable address containing the builder's current values
     * @throws IllegalStateException when the required road name is absent
     */
    public Address build() {
        if (roadName == null || roadName.isBlank()) {
            throw new IllegalStateException("Road name is required");
        }
        return new Address(this);
    }

    // These getters are used by Address while it takes the build-time snapshot.
    // Keeping them package-private avoids making the builder's intermediate
    // state part of the public API.
    String getHouseNo() {
        return houseNo;
    }

    String getRoadName() {
        return roadName;
    }

    String getAddressLine1() {
        return addressLine1;
    }

    String getAddressLine2() {
        return addressLine2;
    }
}
