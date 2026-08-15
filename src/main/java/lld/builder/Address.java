package lld.builder;

/**
 * Immutable value object representing an address.
 *
 * <p>An {@code Address} can be created only through {@link AddressBuilder}.
 * The builder is deliberately mutable while it collects values; this finished
 * object is not. Its state is assigned once in the constructor and cannot be
 * changed afterward.</p>
 *
 * <p>The current fields are {@link String}s, which are immutable themselves.
 * If a mutable field such as a {@code List} is added later, this class must
 * make a defensive copy in the constructor and when returning that value.</p>
 */
public final class Address {
    private final String houseNo;
    private final String roadName;
    private final String addressLine1;
    private final String addressLine2;

    /**
     * Takes a snapshot of the builder's values at build time.
     * Later calls on the same builder do not alter this address.
     *
     * <p>Package-private visibility prevents ordinary clients from bypassing
     * {@link AddressBuilder#build()} and its validation.</p>
     */
    Address(AddressBuilder builder) {
        this.houseNo = builder.getHouseNo();
        this.roadName = builder.getRoadName();
        this.addressLine1 = builder.getAddressLine1();
        this.addressLine2 = builder.getAddressLine2();
    }

    /** @return the house or flat number, or {@code null} when not supplied */
    public String getHouseNo() {
        return houseNo;
    }

    /** @return the required road name */
    public String getRoadName() {
        return roadName;
    }

    /** @return the first optional address line */
    public String getAddressLine1() {
        return addressLine1;
    }

    /** @return the second optional address line */
    public String getAddressLine2() {
        return addressLine2;
    }
}
