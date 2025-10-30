// Product Class
class Car {
    private final String engine;
    private final int wheels;
    private final boolean sunroof;

    // Private constructor
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.sunroof = builder.sunroof;
    }

    // Getter methods
    public String getEngine() {
        return engine;
    }

    public int getWheels() {
        return wheels;
    }

    public boolean hasSunroof() {
        return sunroof;
    }

    @Override
    public String toString() {
        return "Car [Engine=" + engine + ", Wheels=" + wheels + ", Sunroof=" + sunroof + "]";
    }

    // Builder Class
    public static class CarBuilder {
        private String engine;
        private int wheels;
        private boolean sunroof;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder()
                .setEngine("V8")
                .setWheels(4)
                .setSunroof(true)
                .build();

        System.out.println(car);
        System.out.println("Engine: " + car.getEngine());
        System.out.println("Wheels: " + car.getWheels());
        System.out.println("Sunroof: " + car.hasSunroof());
    }
}
