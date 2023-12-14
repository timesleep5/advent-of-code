package day_02;

record CubeAmount(COLOR color, int amount) {

    boolean possible() {
        return color.amountIsPossible(amount);
    }
}
