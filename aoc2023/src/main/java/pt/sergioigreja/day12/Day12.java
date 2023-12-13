package pt.sergioigreja.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Day12 {
    List<String> input;
    List<String> springs;
    List<List<Integer>> groupsOfDamagedSprings;

    public Day12() {
        try {
            input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day12/input"));
            springs = new ArrayList<>();

            groupsOfDamagedSprings = new ArrayList<>(input.size());

            for (String line : input) {
                String[] splitLine = line.split(" ");
                springs.add(splitLine[0]);
                List<Integer> damagedGroups = new ArrayList<>();
                for (String string : splitLine[1].split(",")) {
                    damagedGroups.add(Integer.parseInt(string));
                }

                groupsOfDamagedSprings.add(damagedGroups);
            }
        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }

    }

    private void problem2() {
        int i = 0;
        List<Row> rows = new ArrayList<>();
        for (String spring : springs) {
            StringBuilder sb = new StringBuilder(spring);
            int[] copy = this.groupsOfDamagedSprings.get(i).stream().mapToInt(Integer::intValue).toArray();
            for (int j = 0; j < 4; j++) {
                sb.append("?");
                sb.append(spring);
                for (Integer num : copy) {
                    this.groupsOfDamagedSprings.get(i).add(num);
                }
            }
            Row row = new Row(sb.toString(), this.groupsOfDamagedSprings.get(i));
            rows.add(row);
            i += 1;
        }

        long result = 0;
        for (Row row : rows) {
            result += row.countDifferentWays();
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12();
        day12.problem2();
    }
}
