package day_07;

import _general.Day;

import java.util.Arrays;


public class DaySeven extends Day {
    private boolean partOne;
    private Hand[] hands;
    private Hand[] orderedHands;

    public static void main(String[] args) {
        DaySeven daySeven = new DaySeven();
        System.out.println("Part I result: " + daySeven.partOne());
        System.out.println("Part II result: " + daySeven.partTwo());
    }

    @Override
    public int partOne() {
        partOne = true;
        hands = createHands();
        orderedHands = Arrays.copyOf(hands, hands.length);
        sortHandsByRank();
        resultPartOne = sumUpWinnings();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        partOne = false;
        hands = createHands();
        orderedHands = Arrays.copyOf(hands, hands.length);
        sortHandsByRank();
        System.out.println(this);
        resultPartTwo = sumUpWinnings();
        return resultPartTwo;
        //250_582_720 to high
    }

    private int sumUpWinnings() {
        long winnings = 0;
        for (int rank = 0; rank < orderedHands.length; rank++) {
            winnings += orderedHands[rank].getWinning(rank + 1);
        }
        return Math.toIntExact(winnings);
    }

    private void sortHandsByRank() {
        int highestCardIndex;
        for (int handIndex = orderedHands.length - 1; handIndex >= 0; handIndex--) {
            highestCardIndex = findHighestCardIndexStartingAt(handIndex);
            swapCardsAt(handIndex, highestCardIndex);
        }
    }

    private void swapCardsAt(int first, int second) {
        Hand temp = orderedHands[first];
        orderedHands[first] = orderedHands[second];
        orderedHands[second] = temp;
    }

    private int findHighestCardIndexStartingAt(int start) {
        int highestCardIndex = start;
        for (int handIndex = start; handIndex >= 0; handIndex--) {
            if (orderedHands[handIndex].isHigherThan(orderedHands[highestCardIndex])) {
                highestCardIndex = handIndex;
            }
        }
        return highestCardIndex;
    }

    private Hand[] createHands() {
        Hand[] hands = new Hand[input.length];
        for (int line = 0; line < input.length; line++) {
            hands[line] = createHand(input[line]);
        }
        return hands;
    }

    private Hand createHand(String line) {
        CamelCard[] cards = createCards(line.split(" ")[0]);
        int bid = Integer.parseInt(line.split(" ")[1]);
        return new Hand(cards, bid);
    }

    private CamelCard[] createCards(String cardString) {
        CamelCard[] cards = new CamelCard[cardString.length()];
        for (int cardIndex = 0; cardIndex < cards.length; cardIndex++) {
            cards[cardIndex] = createCard(cardString.charAt(cardIndex));
        }
        return cards;
    }

    private CamelCard createCard(char c) {
        return switch (c) {
            case '2' -> new CamelCard(Rank.TWO);
            case '3' -> new CamelCard(Rank.THREE);
            case '4' -> new CamelCard(Rank.FOUR);
            case '5' -> new CamelCard(Rank.FIVE);
            case '6' -> new CamelCard(Rank.SIX);
            case '7' -> new CamelCard(Rank.SEVEN);
            case '8' -> new CamelCard(Rank.EIGHT);
            case '9' -> new CamelCard(Rank.NINE);
            case 'T' -> new CamelCard(Rank.TEN);
            case 'J' -> partOne ? new CamelCard(Rank.JACK) : new CamelCard(Rank.JOKER);
            case 'Q' -> new CamelCard(Rank.QUEEN);
            case 'K' -> new CamelCard(Rank.KING);
            case 'A' -> new CamelCard(Rank.ACE);
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Hand hand : orderedHands) {
            output.append(hand).append("\n");
        }
        return output.toString();
    }
}
