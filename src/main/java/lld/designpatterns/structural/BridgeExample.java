package lld.designpatterns.structural;

/**
 * Separates shape types from drawing platforms, so both can grow independently.
 */
public class BridgeExample {
    interface Renderer {
        String renderCircle(int radius);
    }

    static class SvgRenderer implements Renderer {
        public String renderCircle(int r) {
            return "SVG circle, radius " + r;
        }
    }

    static class RasterRenderer implements Renderer {
        public String renderCircle(int r) {
            return "Pixels for circle, radius " + r;
        }
    }

    // Shape owns a renderer rather than inheriting from a renderer implementation.
    static class Circle {
        private final Renderer renderer;
        private final int radius;

        Circle(Renderer renderer, int radius) {
            this.renderer = renderer;
            this.radius = radius;
        }

        void draw() {
            System.out.println(renderer.renderCircle(radius));
        }
    }

    public static void main(String[] args) {
        new Circle(new SvgRenderer(), 10).draw();
    }
}
