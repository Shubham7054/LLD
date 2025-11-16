// State Interface
interface DocumentState {
    void publish(DocumentContext context);
    void reject(DocumentContext context);
    String getStatus();
}

// Concrete States
class DraftState implements DocumentState {
    public void publish(DocumentContext context) {
        context.setState(new ReviewState());
        System.out.println("Document moved to Review.");
    }
    public void reject(DocumentContext context) {
        System.out.println("Draft cannot be rejected.");
    }
    public String getStatus() { return "Draft"; }
}

class ReviewState implements DocumentState {
    public void publish(DocumentContext context) {
        context.setState(new PublishedState());
        System.out.println("Document published.");
    }
    public void reject(DocumentContext context) {
        context.setState(new DraftState());
        System.out.println("Review rejected, back to Draft.");
    }
    public String getStatus() { return "Review"; }
}

class PublishedState implements DocumentState {
    public void publish(DocumentContext context) {
        System.out.println("Already published.");
    }
    public void reject(DocumentContext context) {
        System.out.println("Cannot reject published document.");
    }
    public String getStatus() { return "Published"; }
}

// Context
class DocumentContext {
    private DocumentState state = new DraftState();
    public void setState(DocumentState state) { this.state = state; }
    public void publish() { state.publish(this); }
    public void reject() { state.reject(this); }
    public void printStatus() { System.out.println("Current State: " + state.getStatus()); }
}

// Demo
public class StatePatternDemo {
    public static void main(String[] args) {
        DocumentContext doc = new DocumentContext();
        doc.printStatus();
        doc.publish();  // Draft -> Review
        doc.printStatus();
        doc.publish();  // Review -> Published
        doc.printStatus();
        doc.reject();   // Cannot reject published
    }
}
