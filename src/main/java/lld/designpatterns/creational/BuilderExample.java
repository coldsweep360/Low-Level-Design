package lld.designpatterns.creational;

/** Builds an immutable user profile without a long, confusing constructor call. */
public class BuilderExample {
    static class UserProfile {
        private final String name, email, city; private final boolean newsletter;
        private UserProfile(Builder b) { name = b.name; email = b.email; city = b.city; newsletter = b.newsletter; }
        public String toString() { return name + ", " + email + ", " + city + ", newsletter=" + newsletter; }

        // The builder holds optional choices while the final object stays immutable.
        static class Builder {
            private final String name, email; private String city = "Not provided"; private boolean newsletter;
            Builder(String name, String email) { this.name = name; this.email = email; }
            Builder city(String city) { this.city = city; return this; }
            Builder subscribeToNewsletter() { newsletter = true; return this; }
            UserProfile build() { return new UserProfile(this); }
        }
    }
    public static void main(String[] args) {
        UserProfile profile = new UserProfile.Builder("Asha", "asha@example.com").city("Pune").subscribeToNewsletter().build();
        System.out.println(profile);
    }
}
