package pt.sergioigreja.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day6 {
    private List<String> input;

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        day6.problem2BinarySearch();
    }

    public Day6() {
        try {
            this.input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day6/input.txt"));
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.toString(), e);
        }
    }

    // They want binary search probably?
    public void problem2() {
        double time = Double.parseDouble(input.get(0).split(":")[1].trim().replaceAll(" ", ""));
        double distance = Double.parseDouble(input.get(1).split(":")[1].trim().replaceAll(" ", ""));

        double start = 1;
        double end = time - 1;

        while (start < end) {
            double remainingTimeStart = time - start;
            if (remainingTimeStart * start > distance) {
                break;
            } else {
                start += 1;
            }
        }

        System.out.printf("Result is: %f", time - start - start + 1);

    }

    public void problem2BinarySearch() {
        double time = Double.parseDouble(input.get(0).split(":")[1].trim().replaceAll(" ", ""));
        double distance = Double.parseDouble(input.get(1).split(":")[1].trim().replaceAll(" ", ""));

        double start = 1;
        double end = time - 1;

        while (start <= end) {
            double middle = start + (end - start) / 2;

            double remainingTimeStart = time - middle;
            if (remainingTimeStart * middle > distance) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        System.out.printf("result: %f", time - start - start + 1);
    }

    public void problem1() {
        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        for (String t : input.get(0).split(":")[1].trim().split("\\s+")) {
            times.add(Integer.parseInt(t));
        }

        for (String d : input.get(1).split(":")[1].trim().split("\\s+")) {
            distances.add(Integer.parseInt(d));
        }

        int result = 1;

        for (int i = 0; i < times.size(); i++) {
            int start = 1;
            int end = times.get(i) - 1;
            int distance = distances.get(i);
            int time = times.get(i);

            while (start < end) {
                int remainingTimeStart = time - start;
                if (remainingTimeStart * start > distance) {
                    break;
                } else {
                    start += 1;
                }
            }
            result *= (time - start) - start + 1;
        }

        System.out.printf("Result is: %d", result);
    }
}
