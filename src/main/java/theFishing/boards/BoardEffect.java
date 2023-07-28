package theFishing.boards;

public class BoardEffect {
    public String name;
    public String description;
    private Runnable onEnter;

    public BoardEffect(String name, String description, Runnable onEnter) {
        this.name = name;
        this.description = description;
        this.onEnter = onEnter;
    }

    public void activate() {
        onEnter.run();
    }
}
