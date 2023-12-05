package pt.sergioigreja.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//correct = 77435348
public class Day5 {
    private Path input;

    public Day5() {
        this.input = Paths.get(
                "/Users/sergioigreja/Projetos/adventofcode2023/aoc2023/src/main/java/pt/sergioigreja/day5/input.txt");
    }

    public void problem2() {
        try {
            List<String> lines = Files.readAllLines(input);
            String[] seeds = lines.get(0).split(":")[1].trim().split("\\s+");
            List<List<Range>> maps = new ArrayList<>();
            double[] result = new double[1];
            result[0] = Double.MAX_VALUE;

            int mapIndex = -1;
            for (int i = 1; i < lines.size(); i++) {
                if (!lines.get(i).isEmpty()) {
                    if (!Character.isDigit(lines.get(i).charAt(0))) {
                        maps.add(new ArrayList<>());
                        mapIndex += 1;
                    } else {
                        String[] numbers = lines.get(i).trim().split("\\s+");
                        Range range = new Range(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]),
                                Double.parseDouble(numbers[2]));
                        maps.get(mapIndex).add(range);
                    }
                }
            }

            for (int i = 0; i < seeds.length; i += 2) {
                double seed = Double.parseDouble(seeds[i]);
                double limit = seed + Double.parseDouble(seeds[i + 1]);
                while (seed < limit) {
                    helper(0, maps, seed, result);
                    seed += 1;
                }
            }

            System.out.printf("Result: %f", result[0]);

        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void problem1() {
        try {
            List<String> lines = Files.readAllLines(input);
            String[] seeds = lines.get(0).split(":")[1].trim().split("\\s+");
            List<List<Range>> maps = new ArrayList<>();
            double[] result = new double[1];
            result[0] = Double.MAX_VALUE;

            int mapIndex = -1;
            for (int i = 1; i < lines.size(); i++) {
                if (!lines.get(i).isEmpty()) {
                    if (!Character.isDigit(lines.get(i).charAt(0))) {
                        maps.add(new ArrayList<>());
                        mapIndex += 1;
                    } else {
                        String[] numbers = lines.get(i).trim().split("\\s+");
                        Range range = new Range(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]),
                                Double.parseDouble(numbers[2]));
                        maps.get(mapIndex).add(range);
                    }
                }
            }

            for (String seed : seeds) {
                helper(0, maps, Double.parseDouble(seed), result);
            }

            System.out.printf("Result: %f", result[0]);

        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private double helper(int mapIndex, List<List<Range>> maps, double value, double[] currentMin) {
        if (mapIndex >= maps.size()) {
            return value;
        }

        boolean found = false;
        for (Range range : maps.get(mapIndex)) {
            if (range.inRange(value)) {
                found = true;
                /*
                 * if (range.getMap().containsKey(value)) {
                 * return range.getMap().get(value);
                 * }
                 */

                double res = helper(mapIndex + 1, maps, range.getDestinationStart() + value - range.getStartingValue(),
                        currentMin);
                // range.getMap().put(value, res);
                break;
            }
        }

        if (!found) {
            value = helper(mapIndex + 1, maps, value, currentMin);
        }

        if (value < currentMin[0]) {
            currentMin[0] = Math.min(currentMin[0], value);
        }

        return value;
    }
}
