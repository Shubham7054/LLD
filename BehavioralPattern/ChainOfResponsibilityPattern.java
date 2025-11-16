// Handler Interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String issue);
}

// Concrete Handlers
class LevelOneSupport extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("password reset")) {
            System.out.println("Level 1 handled: " + issue);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        }
    }
}

class LevelTwoSupport extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("software installation")) {
            System.out.println("Level 2 handled: " + issue);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        }
    }
}

class LevelThreeSupport extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equalsIgnoreCase("server down")) {
            System.out.println("Level 3 handled: " + issue);
        } else {
            System.out.println("Issue cannot be handled: " + issue);
        }
    }
}

// Demo
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        SupportHandler level1 = new LevelOneSupport();
        SupportHandler level2 = new LevelTwoSupport();
        SupportHandler level3 = new LevelThreeSupport();

        level1.setNextHandler(level2);
        level2.setNextHandler(level3);

        level1.handleRequest("password reset");
        level1.handleRequest("software installation");
        level1.handleRequest("server down");
        level1.handleRequest("unknown issue");
    }
}
