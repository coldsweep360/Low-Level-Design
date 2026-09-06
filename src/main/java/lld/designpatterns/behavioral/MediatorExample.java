package lld.designpatterns.behavioral;

/** A chat room coordinates messages so users do not need references to every other user. */
public class MediatorExample {
    static class ChatRoom { void send(String from, String message) { System.out.println(from + " says: " + message); } }
    static class User { private final String name; private final ChatRoom room; User(String name, ChatRoom room) { this.name = name; this.room = room; } void send(String message) { room.send(name, message); } }
    public static void main(String[] args) { ChatRoom room = new ChatRoom(); new User("Ravi", room).send("Hello everyone"); }
}
