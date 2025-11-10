// Subsystems
class DVDPlayer {
    void on() { System.out.println("DVD Player ON"); }
    void play() { System.out.println("Playing DVD"); }
}

class Projector {
    void on() { System.out.println("Projector ON"); }
}

class SoundSystem {
    void on() { System.out.println("Sound System ON"); }
}

// Facade
class HomeTheaterFacade {
    private DVDPlayer dvd;
    private Projector projector;
    private SoundSystem sound;

    public HomeTheaterFacade(DVDPlayer dvd, Projector projector, SoundSystem sound) {
        this.dvd = dvd;
        this.projector = projector;
        this.sound = sound;
    }

    public void watchMovie() {
        System.out.println("Starting Movie...");
        projector.on();
        sound.on();
        dvd.on();
        dvd.play();
    }
}

// Client
public class FacadePatternDemo {
    public static void main(String[] args) {
        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();
               SoundSystem sound = new SoundSystem();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvd, projector, sound);
        homeTheater.watchMovie();
    }
}
