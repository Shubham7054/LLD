
// Visitor Interface
interface Visitor {
    void visit(Book book);
    void visit(Electronics electronics);
}

// Concrete Visitor: Discount Calculator
class DiscountVisitor implements Visitor {
    public void visit(Book book) {
        System.out.println("Discount on Book: " + (book.getPrice() * 0.10));
    }
    public void visit(Electronics electronics) {
        System.out.println("Discount on Electronics: " + (electronics.getPrice() * 0.05));
    }
}

// Element Interface
interface Product {
    void accept(Visitor visitor);
    double getPrice();
}

// Concrete Elements
class Book implements Product {
    private double price;
    Book(double price) { this.price = price; }
    public double getPrice() { return price; }
    public void accept(Visitor visitor) { visitor.visit(this); }
}

class Electronics implements Product {
    private double price;
    Electronics(double price) { this.price = price; }
    public double getPrice() { return price; }
    public void accept(Visitor visitor) { visitor.visit(this); }
}

// Client
public class VisitorPatternDemo {
    public static void main(String[] args) {
        Product book = new Book(500);
               Product phone = new Electronics(20000);

        Visitor discountVisitor = new DiscountVisitor();

        book.accept(discountVisitor);
        phone.accept(discountVisitor);
    }
