package lld.designpatterns.creational;

/** Copies a configured document instead of rebuilding it field by field. */
public class PrototypeExample {
    static class Document implements Cloneable {
        private String title; private final String template;
        Document(String title, String template) { this.title = title; this.template = template; }
        void setTitle(String title) { this.title = title; }
        // clone is the prototype operation; this class only has immutable/String fields, so a shallow copy is safe here.
        public Document clone() { try { return (Document) super.clone(); } catch (CloneNotSupportedException e) { throw new AssertionError(e); } }
        public String toString() { return title + " using " + template; }
    }
    public static void main(String[] args) {
        Document original = new Document("January report", "company-report-template");
        Document copy = original.clone(); copy.setTitle("February report");
        System.out.println(original); System.out.println(copy);
    }
}
