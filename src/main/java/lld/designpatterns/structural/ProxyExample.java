package lld.designpatterns.structural;

/**
 * Checks permission before allowing a caller to use a sensitive real service.
 */
public class ProxyExample {
    interface Report {
        void view();
    }

    static class RealReport implements Report {
        public void view() {
            System.out.println("Showing confidential report");
        }
    }

    // The proxy has the same interface and decides whether to forward the request.
    static class ReportProxy implements Report {
        private final boolean allowed;
        private final Report real = new RealReport();

        ReportProxy(boolean allowed) {
            this.allowed = allowed;
        }

        public void view() {
            if (allowed) real.view();
            else System.out.println("Access denied");
        }
    }

    public static void main(String[] args) {
        new ReportProxy(false).view();
        new ReportProxy(true).view();
    }
}
