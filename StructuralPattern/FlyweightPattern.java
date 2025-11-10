import java.util.HashMap;

// Flyweight
interface Shape {
    void draw(int x, int y);
}

// ConcreteFlyweight
class Circle implements Shape {
    private String color; // intrinsic state

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing " + color + " circle at (" + x + "," + y + ")");
    }
}

// FlyweightFactory
class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Shape circle = circleMap.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating new circle of color: " + color);
        }
        return circle;
    }
}

// Client
public class FlyweightDemo {
       public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Shape circle = ShapeFactory.getCircle("Red");
            circle.draw(i, i * 2);
        }
    }
}
