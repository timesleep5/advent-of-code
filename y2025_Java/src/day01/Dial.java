package day01;

public class Dial {
    private static final int NUMBERS = 100;
    private static final int STARTING_NUMBER = 50;
    private Number startingNumber;
    private Number current;

    public Dial() {
        buildNumbers();
    }

    void reset() {
        current = startingNumber;
    }

    private void buildNumbers() {
        var start = new Number(0, null);
        var left = start;
        for (int i = 1; i < NUMBERS; i++) {
            var number = new Number(i, left);
            left.right = number;
            left = number;
            if (i == STARTING_NUMBER) {
                startingNumber= number;
            }
        }
        left.right = start;
        start.left = left;
    }

    public int getCurrentValue() {
        return current.value;
    }

    public void moveLeft(int steps) {
        for (int i = 0; i < steps; i++) {
            current = current.left();
        }
    }

    public void moveRight(int steps) {
        for (int i = 0; i < steps; i++) {
            current = current.right();
        }
    }
}
