package day_11;

import java.util.Objects;

public record Galaxy(int x, int y) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return x == galaxy.x && y == galaxy.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
