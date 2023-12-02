package pt.sergioigreja.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    private File input;

    // right-53539
    public Day1() {
        input = new File(
                "/Users/sergioigreja/Projetos/adventofcode2023/aoc2023/src/main/java/pt/sergioigreja/day1/input");
    }

    public int sumAllCalibrationValuesPart2() {
        int result = 0;
        Trie trie = new Trie();

        List<String> words = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        List<String> reverse = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            reverse.add(sb.reverse().toString());
            sb.setLength(0);
        }
        trie.insert(words);
        trie.insert(reverse);

        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int firstDigit = -1;
                int lastDigit = -1;

                for (int i = 0; i < line.length() && firstDigit == -1; i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        firstDigit = Character.getNumericValue(c);
                        break;
                    }
                    TrieNode root = trie.getRoot();
                    if (root.getChildren().containsKey(c)) {
                        for (int j = i; j < line.length(); j++) {
                            if (root.getChildren().containsKey(line.charAt(j))) {
                                root = root.getChildren().get(line.charAt(j));
                                if (root.isLeaf() != -1) {
                                    firstDigit = root.isLeaf();
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }

                for (int i = line.length() - 1; i >= 0 && lastDigit == -1; i--) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        lastDigit = Character.getNumericValue(c);
                        break;
                    }
                    TrieNode root = trie.getRoot();
                    if (root.getChildren().containsKey(c)) {
                        for (int j = i; j >= 0; j--) {
                            if (root.getChildren().containsKey(line.charAt(j))) {
                                root = root.getChildren().get(line.charAt(j));
                                if (root.isLeaf() != -1) {
                                    lastDigit = root.isLeaf();
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }

                result += firstDigit * 10 + lastDigit;
            }
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            return -10;
        }
    }

    public int sumAllCalibrationValues() {
        int result = 0;
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int firstDigit = -1;
                int lastDigit = -1;

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        firstDigit = Character.getNumericValue(c);
                        break;
                    }
                }

                for (int i = line.length() - 1; i >= 0; i--) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        lastDigit = Character.getNumericValue(c);
                        break;
                    }
                }
                System.out.printf("First digit: %d | Last digit: %d\n", firstDigit, lastDigit);

                result += firstDigit * 10 + lastDigit;
            }
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }
}
