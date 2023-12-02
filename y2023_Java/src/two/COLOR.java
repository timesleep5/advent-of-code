package two;

enum COLOR {
    RED(12),
    GREEN(13),
    BLUE(14);

    private final int amount;
    COLOR(int amount) {
        this.amount = amount;
    }

    boolean amountIsPossible(int checkedAmount) {
        return checkedAmount <= this.amount;
    }
}