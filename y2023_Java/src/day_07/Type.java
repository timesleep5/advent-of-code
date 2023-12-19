package day_07;

public enum Type {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private final int order;

    Type(int order) {
        this.order = order;
    }

    boolean isHigherThan(Type type) {
        return this.order > type.order;
    }
}
