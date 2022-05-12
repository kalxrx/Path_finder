import java.util.Objects;

class Position {
    public int x;       //x position
    public int y;       //y position
    public Position previous;

    public Position(int x, int y, Position previous) {
        this.x = x;         //initialize x position
        this.y = y;         //initialize y position
        this.previous = previous;
    }
    //overriding String
    @Override
    public String toString() { return String.format("(%d, %d)", x, y); }

    // overriding the default equals method
    @Override
    public boolean equals(Object o) {
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Position offset(int ox, int oy) { return new Position(x + ox, y + oy, this);  }
}