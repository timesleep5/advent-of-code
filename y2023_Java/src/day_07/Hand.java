package day_07;

import java.util.*;

public class Hand {
    private final CamelCard[] cards;
    private final int bid;
    private final Type type;

    Hand(CamelCard[] cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        type = findTypeOfHand();
    }

    private Type findTypeOfHand() {
        List<Integer> occurrences = getUniqueOccurrences();
        int highestOccurrence = occurrences.isEmpty() ? 5 : Collections.max(occurrences) + getAmountOfJokers();
        return switch (highestOccurrence) {
            case 5 -> Type.FIVE_OF_A_KIND;
            case 4 -> Type.FOUR_OF_A_KIND;
            case 3 ->
                    occurrences.contains(3) && occurrences.contains(2) || Collections.frequency(occurrences, 2) == 2 ? Type.FULL_HOUSE : Type.THREE_OF_A_KIND;
            case 2 -> (Collections.frequency(occurrences, 2) == 2) ? Type.TWO_PAIR : Type.ONE_PAIR;
            case 1 -> Type.HIGH_CARD;
            default -> null;
        };
    }

    private List<Integer> getUniqueOccurrences() {
        List<CamelCard> uniqueCards = getUniqueCards();
        List<Integer> occurrences = new ArrayList<>();
        for (CamelCard currentCard : uniqueCards) {
            occurrences.add(countOccurrencesOf(currentCard));
        }
        return occurrences;
    }

    private List<CamelCard> getUniqueCards() {
        List<CamelCard> uniqueCards = new ArrayList<>();
        for (CamelCard card : cards) {
            if (!containsRankOf(uniqueCards, card) && !card.equals(new CamelCard(Rank.JOKER))) {
                uniqueCards.add(card);
            }
        }
        return uniqueCards;
    }

    private boolean containsRankOf(List<CamelCard> uniqueCards, CamelCard checkedCard) {
        for (CamelCard card : uniqueCards) {
            if (card.equals(checkedCard)) {
                return true;
            }
        }
        return false;
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

    boolean isHigherThan(Hand hand) {
        if (this.type == hand.type) {
            return isHigherBySecondOrderRuleThan(hand);
        } else {
            return isHigherByFirstOrderRuleThan(hand);
        }
    }

    private boolean isHigherByFirstOrderRuleThan(Hand hand) {
        return this.type.isHigherThan(hand.type);
    }

    private boolean isHigherBySecondOrderRuleThan(Hand hand) {
        for (int cardIndex = 0; cardIndex < cards.length; cardIndex++) {
            if (!cards[cardIndex].equals(hand.cards[cardIndex])) {
                return cards[cardIndex].hasHigherRankThan(hand.cards[cardIndex]);
            }
        }
        return false;
    }

    int getWinning(int rank) {
        return rank * bid;
    }

    private int getAmountOfJokers() {
        int jokerAmount = 0;
        for (CamelCard card : cards) {
            if (card.equals(new CamelCard(Rank.JOKER))) {
                jokerAmount++;
            }
        }
        return jokerAmount;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(type.toString()).append(":\n");
        for (CamelCard card : cards) {
            output.append(card).append("\n");
        }
        return output.toString();
    }
}
