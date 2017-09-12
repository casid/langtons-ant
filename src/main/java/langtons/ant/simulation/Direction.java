package langtons.ant.simulation;

public enum Direction {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction rotateRight() {
        return fromOrdinal(this.ordinal() + 1);
    }

    public Direction rotateLeft() {
        return fromOrdinal(this.ordinal() - 1);
    }

    private Direction fromOrdinal(int ordinal) {
        if (ordinal < 0) {
            ordinal += values().length;
        } else if (ordinal >= values().length) {
            ordinal -= values().length;
        }
        return values()[ordinal];
    }
}
