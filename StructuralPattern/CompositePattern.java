// Component
interface FileComponent {
    void showDetails();
}

// Leaf
class File implements FileComponent {
    private String name;
    public File(String name) {
        this.name = name;
    }
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Composite
class Folder implements FileComponent {
    private String name;
    private java.util.List<FileComponent> components = new java.util.ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileComponent component) {
        components.add(component);
    }

    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileComponent component : components) {
            component.showDetails();
        }
    }
}

// Client
public class CompositePatternDemo {
    public static void main(String[] args) {
        FileComponent file1 = new File("Resume.docx");
        FileComponent file2 = new File("Photo.jpg");

        Folder folder = new Folder("My Documents");
        folder.add(file1);
        folder.add(file2);

        folder.showDetails();
    }
}
``
