package pt.sergioigreja.day9;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Day9 {
    List<String> input;
    int[][] readings;

    public Day9() {
        try {
            this.input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day9/input"));
            this.readings = new int[input.size()][];

            int i = 0;
            for (String line : input) {
                String[] numbers = line.split(" ");
                readings[i] = new int[numbers.length];

                for (int j = 0; j < numbers.length; j++) {
                    readings[i][j] = Integer.parseInt(numbers[j]);
                }

                i += 1;
            }

            for (int[] reading : readings) {
                System.out.println(Arrays.toString(reading));
            }

            problem1();

        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }
    }

    // 1994899286
    // 1994899286
    // 1995001648
    // 727858388
    private void problem1() {
        Long result = 0L;
        for (int[] reading : readings) {
            int computation = computeReading(reading);
            System.out.println(computation);
            result += computation;
        }
        System.out.println(result);
    }

    private void problem2() {
        int result = 0;

        int i = 0;
        for (String line : input) {
            String[] numbers = line.split(" ");
            readings[i] = new int[numbers.length];
            int k = 0;
            for (int j = numbers.length - 1; j >= 0; j--) {
                readings[i][j] = Integer.parseInt(numbers[k]);
                k += 1;
            }

            i += 1;
        }

        for (int[] reading : readings) {
            int computation = computeReading(reading);
            System.out.println(computation);
            result += computation;
        }

        System.out.println(result);

    }

    private int computeReading(int[] reading) {
        System.out.println(Arrays.toString(reading));
        boolean allZeros = true;

        for (int i : reading) {
            if (i != 0) {
                allZeros = false;
            }
        }

        if (allZeros) {
            return 0;
        }

        int[] result = new int[reading.length - 1];

        for (int i = 0; i < reading.length - 1; i++) {
            result[i] = reading[i + 1] - reading[i];
        }

        return computeReading(result) + reading[reading.length - 1];
    }

    public static void main(String[] args) {
        Day9 day9 = new Day9();
        day9.problem2();
    }
}
