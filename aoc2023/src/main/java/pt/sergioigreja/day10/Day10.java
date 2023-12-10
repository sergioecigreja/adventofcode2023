package pt.sergioigreja.day10;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Day10 {
    List<String> input;
    Pipe[][] pipes;
    Pipe startingPoint;
    int line;
    int column;
    Set<Pipe> pathPipes;

    public Day10() {
        try {
            this.input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day10/input"));
        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }

        this.pipes = inputToPipes(input);

        problem2();
    }

    private void problem2() {
        pathPipes = new HashSet<>();
        problem1(pathPipes);

        for (int line = 0; line < pipes.length; line++) {
            for (int column = 0; column < pipes[0].length; column++) {
                if (!pathPipes.contains(pipes[line][column])) {
                    pipes[line][column] = null;
                }
            }
        }

        int counter = 0;
        boolean insideLoop = false;

        for (int line = 0; line < pipes.length; line++) {
            for (int column = 0; column < pipes[0].length; column++) {
                if (pipes[line][column] != null) {
                    switch (pipes[line][column].getValue()) {
                        case '|', 'J', 'L':
                            insideLoop = !insideLoop;
                            break;
                        default:
                            break;
                    }
                } else {
                    if (insideLoop) {
                        counter += 1;
                        pipes[line][column] = new Pipe('I');
                    }
                }
            }
            insideLoop = false;
        }

        System.out.println(counter);

        writeToFile();
    }

    private void problem1(Set<Pipe> visited) {
        visited.add(startingPoint);
        Pipe next = startingPoint.getRight();
        if (next == null) {
            next = startingPoint.getLeft();
        }
        if (next == null) {
            next = startingPoint.getDown();
        }

        int steps = traverse(startingPoint, next, visited, 0);
        int result = steps / 2;

        if (steps % 2 != 0) {
            result += 1;
        }

        System.out.println(result);
    }

    private int traverse(Pipe previous, Pipe current, Set<Pipe> visited, int count) {
        if (current.getValue() == 'S') {
            return count;
        }
        if (visited.contains(current)) {
            return -1;
        }
        visited.add(current);
        return traverse(current, current.next(previous), visited, count + 1);
    }

    private Pipe[][] inputToPipes(List<String> input) {
        Pipe[][] result = new Pipe[input.size()][input.get(0).length()];

        for (int line = 0; line < input.size(); line++) {
            for (int column = 0; column < input.get(line).length(); column++) {
                if (input.get(line).charAt(column) != '.') {
                    result[line][column] = new Pipe(input.get(line).charAt(column));
                    if (result[line][column].getValue() == 'S') {
                        this.startingPoint = result[line][column];
                    }
                }
            }
        }

        for (int line = 0; line < result.length; line++) {
            for (int column = 0; column < result[0].length; column++) {
                if (result[line][column] == null) {
                    continue;
                }
                int up = line - 1;
                int down = line + 1;
                int right = column + 1;
                int left = column - 1;

                if (up >= 0) {
                    result[line][column].setUp(result[up][column]);
                }

                if (down < result.length) {
                    result[line][column].setDown(result[down][column]);
                }

                if (right < result[0].length) {
                    result[line][column].setRight(result[line][right]);
                }
                if (left >= 0) {
                    result[line][column].setLeft(result[line][left]);
                }

                if (!result[line][column].isRelevant()) {
                    result[line][column] = null;
                }
            }
        }

        return result;
    }

    public void writeToFile() {
        try {
            File f = new File("src/main/java/pt/sergioigreja/day10/output");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.pipes.length; i++) {
                for (int j = 0; j < this.pipes[0].length; j++) {
                    if (pipes[i][j] == null) {
                        sb.append("* ");
                    } else {
                        sb.append(pipes[i][j].getValue());
                        sb.append(' ');
                    }
                }
                sb.append('\n');
                bw.write(sb.toString());
                sb.setLength(0);
            }
            bw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private int trim(Pipe[][] pipes) {
        int counter = 0;
        for (int i = 0; i < pipes.length; i++) {
            for (int j = 0; j < pipes[0].length; j++) {
                if (pipes[i][j] != null && !pipes[i][j].isRelevant()) {
                    pipes[i][j] = null;
                    counter += 1;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10();
    }
}
