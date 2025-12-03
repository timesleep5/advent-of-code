package day03;

public class JoltageOptimizer {
    static long calculateOptimalJoltage(Bank bank, int numberOfBatteries) {
        var indices = new int[numberOfBatteries];
        var indexBefore = -1;
        for (int i = 0; i < numberOfBatteries; i++) {
            indexBefore++;
            var offsetFromEnd = numberOfBatteries - (i + 1);
            var candidates = new Bank(bank.subList(indexBefore, bank.size() - offsetFromEnd));
            indices[i] = indexBefore + findMaxIndex(candidates);
            indexBefore = indices[i];
        }
        return assembleMaxJoltage(bank, indices);
    }

    private static int findMaxIndex(Bank bank) {
        var maxIndex = 0;
        var max = bank.get(maxIndex);
        for (int i = 1; i < bank.size(); i++) {
            if (bank.get(i) > max) {
                max = bank.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static long assembleMaxJoltage(Bank bank, int[] indices) {
        var stringBuilder = new StringBuilder();
        for (var index : indices) {
            stringBuilder.append(bank.get(index));
        }
        return Long.parseLong(stringBuilder.toString());
    }
}
