package lld.designpatterns.behavioral;

/**
 * Passes a support request along levels until somebody can answer it.
 */
public class ChainOfResponsibilityExample {
    static abstract class SupportHandler {
        private SupportHandler next;

        SupportHandler next(SupportHandler handler) {
            next = handler;
            return handler;
        }

        void handle(int level) {
            if (canHandle(level)) answer();
            else if (next != null) next.handle(level);
            else System.out.println("No handler found");
        }

        abstract boolean canHandle(int level);

        abstract void answer();
    }

    static class BasicSupport extends SupportHandler {
        boolean canHandle(int level) {
            return level == 1;
        }

        void answer() {
            System.out.println("Basic support answered");
        }
    }

    static class ExpertSupport extends SupportHandler {
        boolean canHandle(int level) {
            return level == 2;
        }

        void answer() {
            System.out.println("Expert support answered");
        }
    }

    public static void main(String[] args) {
        SupportHandler first = new BasicSupport();
        first.next(new ExpertSupport());
        first.handle(2);
    }
}
