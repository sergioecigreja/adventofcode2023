package pt.sergioigreja.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import pt.sergioigreja.aux.Tuple;

public class Day13 {
    private List<String> input;
    private List<Tuple<List<String>, List<String>>> tuples;
    Map<Tuple<List<String>, List<String>>, Tuple<Integer, Boolean>> map = new HashMap<>();

    // too high 38675
    public Day13() {
        try {
            input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day13/input"));
        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }

        this.tuples = new ArrayList<>();
        List<List<String>> patterns = new ArrayList<>();

        List<String> pattern = new ArrayList<>();
        for (String line : input) {
            if (!line.isBlank()) {
                pattern.add(line);
            } else {
                patterns.add(pattern);
                pattern = new ArrayList<>();
            }
        }
        patterns.add(pattern);

        for (List<String> p : patterns) {
            Tuple<List<String>, List<String>> tuple = new Tuple<>(p, new ArrayList<>());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < p.get(0).length(); i++) {
                for (int j = 0; j < p.size(); j++) {
                    sb.append(p.get(j).charAt(i));
                }
                tuple.y.add(sb.toString());
                sb.setLength(0);
            }

            tuples.add(tuple);
        }
    }

    public void problem1() {
        int result = 0;

        for (Tuple<List<String>, List<String>> tuple : this.tuples) {
            int count = countUntilMirror(tuple.x);
            if (count == 0) {
                count = countUntilMirror(tuple.y);
                result += count;
                this.map.put(tuple, new Tuple<>(count, false));
            } else {
                this.map.put(tuple, new Tuple<>(count, true));
                result += count * 100;
            }
        }

        System.out.printf("Result is: %d\n", result);
    }

    public void problem2() {
        int result = 0;
        for (Tuple<List<String>, List<String>> tuple : this.tuples) {

            int count = countUntilMirror2(tuple, true);

            if (count == 0) {
                count = countUntilMirror2(tuple, false);
                result += count;

            } else {
                result += count * 100;
            }
        }

        System.out.printf("Result is: %d\n", result);
    }

    private boolean canHaveSmudge(String prev, String curr) {
        if (prev.equals("")) {
            return false;
        }

        boolean isDifferent = false;
        for (int i = 0; i < prev.length(); i++) {
            if (prev.charAt(i) != curr.charAt(i)) {
                if (isDifferent) {
                    return false;
                }
                isDifferent = true;
            }
        }

        return isDifferent;
    }

    private int countUntilMirror2(Tuple<List<String>, List<String>> tuple, boolean x) {
        int result = 0;
        List<String> lines = x ? tuple.x : tuple.y;

        for (int i = 1; i < lines.size(); i++) {
            if (isValidMirror2(i, tuple, x)) {
                return i;
            }
        }

        return result;
    }

    private int countUntilMirror(List<String> lines) {
        int result = 0;
        String prev = "";

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(prev)) {
                if (isValidMirror(lines, i)) {
                    return i;
                }
            }
            prev = lines.get(i);
        }

        return result;
    }

    private boolean isValidMirror(List<String> lines, int mirrorIndex) {
        int prev = mirrorIndex - 1;
        boolean result = true;
        while (prev >= 0 && mirrorIndex < lines.size() && result) {
            result = lines.get(prev).equals(lines.get(mirrorIndex));
            prev -= 1;
            mirrorIndex += 1;
        }
        return result;
    }

    private boolean isValidMirror2(int mirrorIndex, Tuple<List<String>, List<String>> tuple,
            boolean x) {
        int prev = mirrorIndex - 1;
        boolean cleaned = false;
        boolean result = true;
        int original = mirrorIndex;
        List<String> lines = x ? tuple.x : tuple.y;

        while (prev >= 0 && mirrorIndex < lines.size() && result) {
            result = lines.get(prev).equals(lines.get(mirrorIndex));
            if (result == false && !cleaned) {
                if (canHaveSmudge(lines.get(prev), lines.get(mirrorIndex))) {
                    result = true;
                    cleaned = true;
                }
            }
            prev -= 1;
            mirrorIndex += 1;
        }

        if (result) {
            if (this.map.get(tuple).equals(new Tuple<>(original, x))) {
                return false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13();
        day13.problem1();
        day13.problem2();
    }
}
