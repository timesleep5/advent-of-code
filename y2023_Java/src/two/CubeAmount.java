package two;

record CubeAmount(COLOR color, int amount) {

    boolean possible() {
        return color.amountIsPossible(amount);
    }
}
