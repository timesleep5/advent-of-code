package day_12;

import java.util.Arrays;

public record Record(String damaged, int[] backup){
    @Override
    public String toString() {
        return "Record{" +
                "damaged='" + damaged + '\'' +
                ", backup=" + Arrays.toString(backup) +
                '}';
    }
}
