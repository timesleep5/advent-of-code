package day_07;

public class CamelCard {
    private final Rank rank;

    CamelCard(Rank rank) {
        this.rank = rank;
    }

    boolean hasHigherRankThan(CamelCard card) {
        return this.rank.isHigherThan(card.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CamelCard card = (CamelCard) o;
        return this.rank == card.rank;
    }

    @Override
    public String toString() {
        return rank.toString();
    }
}
