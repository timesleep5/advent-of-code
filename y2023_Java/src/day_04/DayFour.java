package day_04;

import _general.Day;


public class DayFour extends Day {
    private Scratchcard[] scratchcards;

    public static void main(String[] args) {
        DayFour dayFour = new DayFour();
        System.out.println("Part I result: " + dayFour.partOne());
        System.out.println("Part II result: " + dayFour.partTwo());
    }

    public DayFour() {
        scratchcards = new Scratchcard[input.length];
        buildScratchcards();
    }

    @Override
    public int partOne() {
        resultPartOne = sumUpAllPoints();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        createAllCopies();
        resultPartTwo = sumUpAllCopies();
        return resultPartTwo;
    }


    private void buildScratchcards() {
        for (int line = 0; line < input.length; line++) {
            scratchcards[line] = new Scratchcard(input[line]);
        }
    }

    private int sumUpAllPoints() {
        int sum = 0;
        for (Scratchcard scratchcard : scratchcards) {
            sum += scratchcard.getPoints();

        }
        return sum;
    }


    private void createAllCopies() {
        for (Scratchcard card : scratchcards) {
            for (int copy = 1; copy <= card.getNumberOfCopies(); copy++) {
                createCopysFromCardPoints(card);
            }
        }
    }

    private void createCopysFromCardPoints(Scratchcard card) {
        int orderRange = card.getMatchingNumbersCount();
        int orderStart = card.getOrder();
        int orderLimit = Math.min(orderStart + orderRange, scratchcards.length);
        for (int order = orderStart; order < orderLimit; order++) {
            scratchcards[order].addCopy();
        }
    }

    private int sumUpAllCopies() {
        int sum = 0;
        for (Scratchcard scratchcard : scratchcards) {
            sum += scratchcard.getNumberOfCopies();
        }
        return sum;
    }
}
