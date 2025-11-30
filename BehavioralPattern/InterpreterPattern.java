
// Expression interface
interface Expression {
    int interpret();
}

// Terminal expression for numbers
class Number implements Expression {
    private int value;
    public Number(int value) { this.value = value; }
    public int interpret() { return value; }
}

// Non-terminal expression for addition
class Add implements Expression {
    private Expression left, right;
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

// Client
public class InterpreterDemo {
    public static void main(String[] args) {
        Expression expr = new Add(new Number(5), new Add(new Number(3), new Number(2)));
        System.out.println("Result: " + expr.interpret()); // Output: 10
    }
}
