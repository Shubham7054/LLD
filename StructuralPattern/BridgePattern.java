// Implementor
interface Color {
    void applyColor();
}

// ConcreteImplementors
class RedColor implements Color {
    public void applyColor() {
        System.out.println("Applying Red Color");
    }
}

class BlueColor implements Color {
    public void applyColor() {
        System.out.println("Applying Blue Color");
    }
}

// Abstraction
abstract class Shape {
    protected Color color;
    public Shape(Color color) {
        this.color = color;
    }
    abstract void draw();
}

// RefinedAbstractions
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }
    public void draw() {
        System.out.print("Drawing Circle with ");
        color.applyColor();
    }
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }
    public void draw() {
        System.out.print("Drawing Square with ");
        color.applyColor();
    }
}

// Client
publicpublic class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        Shape blueSquare = new Square(new BlueColor());

        redCircle.draw();   // Drawing Circle with Applying Red Color
        blueSquare.draw();  // Drawing Square with Applying Blue Color
    }
}
