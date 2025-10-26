// Product interface
interface Shape {
    void draw();
    double area(); // Added method for area calculation
}

// Concrete Products
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }

    @Override
    public double area() {
        return side * side;
    }
}

// Factory Class
class ShapeFactory {
    public Shape getShape(String type, double dimension) {
        if (type.equalsIgnoreCase("CIRCLE")) return new Circle(dimension);
        else if (type.equalsIgnoreCase("SQUARE")) return new Square(dimension);
        return null;
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape circle = factory.getShape("CIRCLE", 5); // radius = 5
        circle.draw();
        System.out.println("Circle Area: " + circle.area());

        Shape square = factory.getShape("SQUARE", 4); // side = 4
        square.draw();
        System.out.println("Square Area: " + square.area());
    }
}
