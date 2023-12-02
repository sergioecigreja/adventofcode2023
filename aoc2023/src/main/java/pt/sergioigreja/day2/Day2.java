package pt.sergioigreja.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    private File input;

    public Day2() {
        input = new File("src/main/java/pt/sergioigreja/day2/input.txt");
    }

    public int sumValidGamesId(int redCubes, int greenCubes, int blueCubes) {
        int result = 0;

        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().replaceAll("\\s+", "");

                String[] firstSplit = line.split(":");
                int gameId = Integer.parseInt(firstSplit[0].substring(4));

                String[] rounds = firstSplit[1].split(";");
                int maxRed = 0;
                int maxGreen = 0;
                int maxBlue = 0;

                for (String round : rounds) {
                    String[] grabs = round.split(",");
                    for (String grab : grabs) {
                        // "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"
                        String[] numAndColor = grab.split("(?<=\\d)(?=\\D)");
                        switch (numAndColor[1]) {
                            case "red":
                                maxRed = Math.max(maxRed, Integer.parseInt(numAndColor[0]));
                                break;
                            case "green":
                                maxGreen = Math.max(maxGreen, Integer.parseInt(numAndColor[0]));
                                break;
                            case "blue":
                                maxBlue = Math.max(maxBlue, Integer.parseInt(numAndColor[0]));
                            default:
                                break;
                        }
                    }
                }

                result += maxRed * maxGreen * maxBlue;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }

        return result;
    }
}
