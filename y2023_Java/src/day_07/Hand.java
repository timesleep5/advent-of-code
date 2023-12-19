package day_07;

import java.util.*;

public class Hand {
    private CamelCard[] cards = new CamelCard[5];
    private final int bid;
    private final Type type;

    Hand(CamelCard[] cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        type = findTypeOfHand();
    }

    private Type findTypeOfHand() {
        List<Integer> occurrences = getUniqueOccurrences();
        int highestOccurrence = Collections.max(occurrences);
        return switch (highestOccurrence) {
            case 5 -> Type.FIVE_OF_A_KIND;
            case 4 -> Type.FOUR_OF_A_KIND;
            case 3 -> occurrences.contains(2) ? Type.FULL_HOUSE : Type.THREE_OF_A_KIND;
            case 2 -> (Collections.frequency(occurrences, 2) == 2) ? Type.TWO_PAIR : Type.ONE_PAIR;
            case 1 -> Type.HIGH_CARD;
            default -> null;
        };
    }

    private List<Integer> getUniqueOccurrences() {
        Set<CamelCard> uniqueCards = getUniqueCards();
        List<Integer> occurrences = new ArrayList<>();
        for (CamelCard currentCard : uniqueCards) {
            occurrences.add(countOccurrencesOf(currentCard));
        }
        return occurrences;
    }

    private Set<CamelCard> getUniqueCards() {
        return new TreeSet<>(Arrays.asList(cards));
    }

    private Integer countOccurrencesOf(CamelCard currentCard) {
        int counter = 0;
        for (CamelCard card : cards) {
            if (card.equals(currentCard)) {
                counter++;
            }
        }
        return counter;
    }

    boolean isHigherByFirstOrderRuleThan(Hand hand) {
        return this.type.isHigherThan(hand.type);
    }

    boolean isHigherBySecondOrderRuleThan(Hand hand) {
        for (int cardIndex = 0; cardIndex < cards.length; cardIndex++) {
            if (cards[cardIndex].equals(hand.cards[cardIndex])) {}
            else return cards[cardIndex].hasHigherRankThan(hand.cards[cardIndex]);
        }
        return false;
    }

    int getWinning(int rank) {
        return rank * bid;
    }
}
