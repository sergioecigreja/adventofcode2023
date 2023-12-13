package pt.sergioigreja.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Day11 {
    private List<String> input;
    private char[][] universe;
    private Set<Integer> emptyLines;
    private Set<Integer> emptyColumns;
    private List<Galaxy> galaxies;

    // 603050444 603050444 543018317006
    public Day11() {
        try {
            input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day11/input"));
            universe = inputToUniverse(input);
        } catch (IOException e) {
            // TODO: handle exception
            Logger.getGlobal().warning(e.toString());
        }
    }

    public void problem1() {
        this.emptyLines = new HashSet<>();
        this.emptyColumns = new HashSet<>();
        this.galaxies = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < this.universe.length; i++) {
            boolean emptySpaces = true;

            for (int j = 0; j < this.universe[0].length; j++) {
                if (this.universe[i][j] != '.') {
                    emptySpaces = false;
                }
                if (this.universe[i][j] == '#') {
                    count += 1;
                    this.galaxies.add(new Galaxy(i, j, count));
                }

            }
            if (emptySpaces) {
                this.emptyLines.add(i);
            }
        }
        for (int i = 0; i < this.universe[0].length; i++) {
            boolean emptySpaces = true;
            for (int j = 0; j < this.universe.length; j++) {
                if (this.universe[j][i] != '.') {
                    emptySpaces = false;
                    break;
                }
            }
            if (emptySpaces) {
                this.emptyColumns.add(i);
            }
        }

        long result = 0;

        for (int i = 0; i < this.galaxies.size(); i++) {
            for (int j = i + 1; j < this.galaxies.size(); j++) {
                result += calculateDistanceBetweenGalaxies(this.galaxies.get(i), this.galaxies.get(j), emptyLines,
                        emptyColumns);
            }
        }

        System.out.println(result);
    }

    public Long calculateDistanceBetweenGalaxies(Galaxy g1, Galaxy g2, Set<Integer> emptyLines,
            Set<Integer> emptyColumns) {
        long result = 0L;
        long horizontalDistance = Math.abs(g1.getColumn() - g2.getColumn());
        long verticalDistance = Math.abs(g1.getLine() - g2.getLine());

        long emptySpacesBetweenGalaxies = 0;

        for (Integer line : emptyLines) {
            if ((line < g2.getLine() && line > g1.getLine()) || (g1.getLine() > line && g2.getLine() < line)) {
                emptySpacesBetweenGalaxies += 1;
            }
        }

        for (Integer column : emptyColumns) {
            if (column < g1.getColumn() && column > g2.getColumn()
                    || (column > g1.getColumn() && column < g2.getColumn())) {
                emptySpacesBetweenGalaxies += 1;
            }
        }

        result = horizontalDistance + verticalDistance + (999999 * emptySpacesBetweenGalaxies);

        return result;
    }

    private char[][] inputToUniverse(List<String> input) {
        int lines = input.size();
        int columns = input.get(0).length();
        char[][] result = new char[lines][columns];

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = input.get(i).charAt(j);
            }
        }

        return result;
    }

    private void printUniverse(char[][] universe) {
        StringBuilder sb = new StringBuilder();

        for (char[] line : universe) {
            for (char c : line) {
                sb.append(c);
                sb.append(" ");
            }
            sb.append('\n');
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }

    public static void main(String[] args) {
        Day11 day11 = new Day11();
        day11.problem1();
    }
}