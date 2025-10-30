class Car {
    private final String engine;
    private final int wheels;
    private final boolean sunroof;
    private final boolean gps;
    private final boolean heatedSeats;
    private final boolean autonomous;
    private final String color;

    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.sunroof = builder.sunroof;
        this.gps = builder.gps;
        this.heatedSeats = builder.heatedSeats;
        this.autonomous = builder.autonomous;
        this.color = builder.color;
    }

    // Getter methods
    public String getEngine() { return engine; }
    public int getWheels() { return wheels; }
    public boolean hasSunroof() { return sunroof; }
    public boolean hasGPS() { return gps; }
    public boolean hasHeatedSeats() { return heatedSeats; }
    public boolean isAutonomous() { return autonomous; }
    public String getColor() { return color; }

    @Override
    public String toString() {
        return "Car [Engine=" + engine + ", Wheels=" + wheels + ", Sunroof=" + sunroof +
               ", GPS=" + gps + ", HeatedSeats=" + heatedSeats +
               ", Autonomous=" + autonomous + ", Color=" + color + "]";
    }

    public static class CarBuilder {
        private String engine;
        private int wheels;
        private boolean sunroof;
        private boolean gps;
        private boolean heatedSeats;
        private boolean autonomous;
        private String color;

        public CarBuilder setEngine(String engine) { this.engine = engine; return this; }
        public CarBuilder setWheels(int wheels) { this.wheels = wheels; return this; }
        public CarBuilder setSunroof(boolean sunroof) { this.sunroof = sunroof; return this; }
        public CarBuilder setGPS(boolean gps) { this.gps = gps; return this; }
        public CarBuilder setHeatedSeats(boolean heatedSeats) { this.heatedSeats = heatedSeats; return this; }
        public CarBuilder setAutonomous(boolean autonomous) { this.autonomous = autonomous; return this; }
        public CarBuilder setColor(String color) { this.color = color; return this; }

        public Car build() { return new Car(this); }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Car luxuryCar = new Car.CarBuilder()
                .setEngine("Electric")
                .setWheels(4)
                .setSunroof(true)
                .setGPS(true)
                .setHeatedSeats(true)
                .setAutonomous(true)
                .setColor("Metallic Blue")
                .build();

        System.out.println(luxuryCar);
        System.out.println("Engine: " + luxuryCar.getEngine());
        System.out.println("Color: " + luxuryCar.getColor());
        System.out.println("Autonomous: " + luxuryCar.isAutonomous());
    }
}
