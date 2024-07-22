package day_11;

import java.util.Objects;

public record GalaxyPair(Galaxy first, Galaxy second) {
    int shortestPath() {
        int xDistance = Math.abs(first().x() - second().x());
        int yDistance = Math.abs(first().y() - second().y());
        return xDistance + yDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalaxyPair that = (GalaxyPair) o;
        return (Objects.equals(first, that.first) && Objects.equals(second, that.second))
                || (Objects.equals(first, that.second) && Objects.equals(second, that.first));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first) + Objects.hashCode(second);
    }
}
