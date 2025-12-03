package day01;

public class Number {
    public final int value;
    public Number left;
    public Number right;


    public Number(int value, Number left) {
        this.value = value;
        this.left = left;
    }

    public Number left() {
        return left;
    }

    public Number right() {
        return right;
    }
}
