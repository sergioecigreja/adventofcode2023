package pt.sergioigreja.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    private File input;

    public Day4() {
        input = new File("aoc2023/src/main/java/pt/sergioigreja/day4/input.txt");
    }

    public void problem1() {
        try {
            int result = 0;
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                int gameResult = 0;
                String line = scanner.nextLine();
                String card = line.split(":")[1].trim();
                String numbers[] = card.split("\\|");
                String winningNumbers = numbers[0];
                String gameNumbers = numbers[1];
                String[] eachWinningNumbers = winningNumbers.split(" ");
                for (String number : gameNumbers.trim().split("\\s+")) {
                    for (int i = 0; i < eachWinningNumbers.length; i++) {
                        if (number.trim().equals(eachWinningNumbers[i].trim())) {
                            gameResult = gameResult == 0 ? 1 : gameResult * 2;
                        }
                    }
                }
                result += gameResult;
            }

            System.out.printf("The result is : %d", result);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    public int isWinningGame(String game) {
        String numbers[] = game.split("\\|");
        String winningNumbers = numbers[0];
        String gameNumbers = numbers[1];
        String[] eachWinningNumbers = winningNumbers.trim().split("\\s+");
        int result = 0;// number of games to copy
        for (String number : gameNumbers.trim().split("\\s+")) {
            for (int i = 0; i < eachWinningNumbers.length; i++) {
                if (number.trim().equals(eachWinningNumbers[i].trim())) {
                    result += 1;
                }
            }
        }

        return result;
    }

    public void problem2() {
        try {
            int result = 0;
            Scanner scanner = new Scanner(input);
            List<String> games = new ArrayList<>();
            List<Integer> repeating = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String card = line.split(":")[1].trim();
                games.add(card);
            }

            for (int i = 0; i < games.size(); i++) {
                if (repeating.size() != 0) {
                    int times = repeating.remove(0);
                    int gameResult = isWinningGame(games.get(i));

                    if (gameResult != 0) {
                        if (gameResult > repeating.size()) {
                            int aux = gameResult - repeating.size();
                            for (int j = 0; j < aux; j++) {
                                repeating.add(0);
                            }
                        }
                        for (int j = 0; j < gameResult; j++) {
                            repeating.set(j, repeating.get(j) + times + 1);
                        }
                    }
                    result += 1 + times;
                } else {
                    int gameResult = isWinningGame(games.get(i));
                    if (gameResult != 0) {
                        for (int j = 0; j < gameResult; j++) {
                            repeating.add(1);
                        }
                    }
                    result += 1;
                }

            }

            System.out.printf("The result is : %d", result);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }
}
