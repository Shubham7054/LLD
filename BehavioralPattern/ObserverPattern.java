// Observer Interface
interface Observer {
    void update(float temperature);
}

// Subject Interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private float temperature;
    private final java.util.List<Observer> observers = new java.util.ArrayList<>();

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) { observers.add(o); }
    @Override
    public void removeObserver(Observer o) { observers.remove(o); }
    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }
}

// Concrete Observers
class MobileApp implements Observer {
    private final String name;
    public MobileApp(String name) { this.name = name; }
    @Override
    public void update(float temperature) {
        System.out.println(name + " shows temperature: " + temperature + "°C");
    }
}

class LEDDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("LED Display updated: " + temperature + "°C");
    }
}

// Demo
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        Observer app1 = new MobileApp("AppOne");
        Observer app2 = new MobileApp("AppTwo");
        Observer display = new LEDDisplay();

        station.registerObserver(app1);
        station.registerObserver(app2);
        station.registerObserver(display);

        station.setTemperature(25.5f);
        station.setTemperature(30.0f);
    }
}
