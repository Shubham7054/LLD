
// 1. Iterator Interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// 2. Aggregate Interface
interface Playlist {
    Iterator<String> createIterator();
}

// 3. Concrete Aggregate
class SongPlaylist implements Playlist {
    private List<String> songs = new ArrayList<>();

    public void addSong(String song) {
        songs.add(song);
    }

    @Override
    public Iterator<String> createIterator() {
        return new SongIterator(songs);
    }
}

// 4. Concrete Iterator
class SongIterator implements Iterator<String> {
    private List<String> songs;
    private int position = 0;

    public SongIterator(List<String> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public String next() {
        return songs.get(position++);
    }
}

// 5. Client
public class IteratorPatternDemo {
    public static void main(String[] args) {
        SongPlaylist playlist = new SongPlaylist();
        playlist.addSong("Shape of You");
               playlist.addSong("Blinding Lights");
        playlist.addSong("Levitating");

        Iterator<String> iterator = playlist.createIterator();
        while (iterator.hasNext()) {
            System.out.println("Playing: " + iterator.next());
        }
    }
