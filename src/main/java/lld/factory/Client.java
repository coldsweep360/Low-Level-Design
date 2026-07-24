package lld.factory;

import lld.factory.factory.ShapeFactory;
import lld.factory.products.Shape;

public class Client {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("circle");
        shape.draw();
    }
}
