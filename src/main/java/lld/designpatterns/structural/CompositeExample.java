package lld.designpatterns.structural;

import java.util.ArrayList;
import java.util.List;

/** Lets a folder and a file share the same operation: show their size. */
public class CompositeExample {
    interface FileSystemItem { int size(); }
    static class File implements FileSystemItem { private final int bytes; File(int bytes) { this.bytes = bytes; } public int size() { return bytes; } }
    static class Folder implements FileSystemItem {
        private final List<FileSystemItem> children = new ArrayList<>();
        void add(FileSystemItem child) { children.add(child); }
        // A folder delegates to children; children may themselves be folders.
        public int size() { return children.stream().mapToInt(FileSystemItem::size).sum(); }
    }
    public static void main(String[] args) { Folder root = new Folder(); root.add(new File(10)); Folder photos = new Folder(); photos.add(new File(90)); root.add(photos); System.out.println(root.size() + " bytes"); }
}
