package lld.ConnectFour;

public class Player {
    private String id;
    private DiscColor color;
    private String name;

    public Player(String id, DiscColor color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public DiscColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
