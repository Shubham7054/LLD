// Abstract Class
abstract class DataExporter {
    // Template Method
    public final void export() {
        readData();
        processData();
        writeData();
    }

    protected abstract void readData();
    protected abstract void processData();
    protected abstract void writeData();
}

// Concrete Class: CSV Exporter
class CSVDataExporter extends DataExporter {
    @Override
    protected void readData() {
        System.out.println("Reading data from CSV file...");
    }
    @Override
    protected void processData() {
        System.out.println("Processing CSV data...");
    }
    @Override
    protected void writeData() {
        System.out.println("Writing processed data to CSV...");
    }
}

// Concrete Class: Database Exporter
class DatabaseDataExporter extends DataExporter {
    @Override
    protected void readData() {
        System.out.println("Reading data from Database...");
    }
    @Override
    protected void processData() {
        System.out.println("Processing Database data...");
    }
    @Override
    protected void writeData() {
        System.out.println("Writing processed data to Database...");
    }
}

// Demo
public class TemplateMethodDemo {
    public static void main(String[] args) {
        DataExporter csvExporter = new CSVDataExporter();
        csvExporter.export();

        System.out.println();

        DataExporter dbExporter = new DatabaseDataExporter();
        dbExporter.export();
    }
}
