package lld.MeetingRoom;

public class MeetingRoom {
    private final String id;
    private final int capacity;

    public MeetingRoom(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

}
