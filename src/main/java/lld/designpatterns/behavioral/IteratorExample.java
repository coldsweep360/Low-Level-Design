package lld.designpatterns.behavioral;

import java.util.Iterator;
import java.util.List;

/** Provides a controlled way to walk a playlist without revealing its chosen storage details. */
public class IteratorExample {
    static class Playlist implements Iterable<String> {
        private final List<String> songs = List.of("Morning", "Noon", "Night");
        // Clients receive Java's Iterator interface, not the internal List itself.
        public Iterator<String> iterator() { return songs.iterator(); }
    }
    public static void main(String[] args) { for (String song : new Playlist()) System.out.println(song); }
}
