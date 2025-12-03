package day03;

import java.util.ArrayList;
import java.util.List;

public class Bank extends ArrayList<Integer> {
    Bank(String joltages) {
        for (char joltage : joltages.toCharArray()) {
            this.add(Integer.parseInt(joltage + ""));
        }
    }

    Bank(List<Integer> joltages) {
        this.addAll(joltages);
    }
}
