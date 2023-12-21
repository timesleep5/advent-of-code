package day_09;

public class History {
    private History parentHistory;
    private final int[] values;
    private final boolean lastLayer;

    History(int[] values) {
        this.values = values;
        this.lastLayer = checkForLastLayer();
    }

    History(History parent) {
        parentHistory = parent;
        this.values = getValuesFromParentValues(parent.values);
        lastLayer = checkForLastLayer();
    }

    History getLastLayer() {
        if (lastLayer) {
            return this;
        }
        else {
            return new History(this).getLastLayer();
        }
    }

    private int[] getValuesFromParentValues(int[] parentValues) {
        int[] values = new int[parentValues.length - 1];
        for (int valueIndex = 0; valueIndex < values.length; valueIndex++) {
            values[valueIndex] = parentValues[valueIndex + 1] - parentValues[valueIndex];
        }
        return values;
    }

    private boolean checkForLastLayer() {
        for (int value : values) {
            if (value != 0) return false;
        }
        return true;
    }

    int extrapolateNewValue(int childValue) {
        int extrapolatedValue = values[values.length - 1] + childValue;
        if (parentHistory != null) {
            return parentHistory.extrapolateNewValue(extrapolatedValue);
        } else {
            return extrapolatedValue;
        }
    }

    int extrapolateNewBackwardsValue(int childValue) {
        int extrapolatedValue = values[0] - childValue;
        if (parentHistory != null) {
            return parentHistory.extrapolateNewBackwardsValue(extrapolatedValue);
        } else {
            return extrapolatedValue;
        }
    }
}
