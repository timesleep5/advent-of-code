package day03;

import _general.Input;

import java.math.BigInteger;
import java.util.List;

public class PowerDistributor {
    private final List<Bank> banks;

    public PowerDistributor(Input input) {
        this.banks = buildBanks(input);
    }

    private List<Bank> buildBanks(Input input) {
        return input.lines().stream().map(Bank::new).toList();
    }

    public String sumUpMaxJoltages(int numberOfBatteries) {
        BigInteger sum = banks.stream()
                .map(b -> BigInteger.valueOf(JoltageOptimizer.calculateOptimalJoltage(b, numberOfBatteries)))
                .reduce(BigInteger.ZERO, BigInteger::add);
        return sum.toString();
    }
}
