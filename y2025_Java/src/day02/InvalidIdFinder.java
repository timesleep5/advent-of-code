package day02;

import _general.Input;

import java.math.BigInteger;

public class InvalidIdFinder {
    private final Database database;

    public InvalidIdFinder(Input input) {
        this.database = new Database(input);
    }

    public String sumUpInvalidIds() {
        var sum = BigInteger.ZERO;
        for (String id : database) {
            if (IdChecker.isInvalid(id)) {
                sum = sum.add(new BigInteger(id));
            }
        }
        return String.valueOf(sum);
    }


    public String sumUpInvalidStrictIds() {
        var sum = BigInteger.ZERO;
        for (String id : database) {
            if (IdChecker.isInvalidStrict(id)) {
                sum = sum.add(new BigInteger(id));
            }
        }
        return String.valueOf(sum);
    }
}
