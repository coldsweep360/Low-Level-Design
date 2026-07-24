package lld.factory.factory;

import lld.factory.products.Circle;
import lld.factory.products.Rectangle;
import lld.factory.products.Shape;
import lld.factory.products.Square;

public class ShapeFactory {
    public Shape getShape(String shape){
        switch(shape) {
            case "circle":
                return new Circle();

            case "square":
                return new Square();

            case "rectangle":
                return new Rectangle();

            default:
                throw new IllegalStateException("Unexpected value: " + shape);
        }

    }
}
