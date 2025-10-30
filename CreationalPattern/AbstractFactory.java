// Abstract Products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

// Concrete Products - Windows
class WindowsButton implements Button {
    public void render() { System.out.println("Rendering Windows Button"); }
}

class WindowsCheckbox implements Checkbox {
    public void render() { System.out.println("Rendering Windows Checkbox"); }
}

// Concrete Products - MacOS
class MacButton implements Button {
    public void render() { System.out.println("Rendering MacOS Button"); }
}

class MacCheckbox implements Checkbox {
    public void render() { System.out.println("Rendering MacOS Checkbox"); }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factories
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Client
public class Main {
    public static void main(String[] args) {
        GUIFactory factory;

        String os = "Mac"; // Could be detected dynamically
        if (os.equalsIgnoreCase("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.render();
    }
}
