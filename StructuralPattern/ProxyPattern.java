// Subject interface
interface Service {
    void request();
}

// Real Subject
class RealService implements Service {
    public void request() {
        System.out.println("Executing request in RealService");
    }
}

// Proxy
class ServiceProxy implements Service {
    private RealService realService;

    @Override
    public void request() {
        if (realService == null) {
            realService = new RealService(); // Lazy initialization
        }
        System.out.println("Proxy: Performing access control before calling RealService");
        realService.request();
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Service service = new ServiceProxy();
        service.request();
    }
}
