
// Target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee
class AdvancedMediaPlayer {
    void playVideo(String fileName) {
        System.out.println("Playing video: " + fileName);
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter() {
        advancedPlayer = new AdvancedMediaPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("video")) {
            advancedPlayer.playVideo(fileName);
        } else {
            System.out.println("Playing audio: " + fileName);
        }
    }
}

// Client
public class AdapterPatternDemo {
       public static void main(String[] args) {
        MediaPlayer player = new MediaAdapter();
        player.play("audio", "song.mp3");
        player.play("video", "movie.mp4");
    }
}
