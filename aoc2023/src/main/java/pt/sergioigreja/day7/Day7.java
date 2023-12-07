package pt.sergioigreja.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Day7 {
    private List<String> input;
    static Map<Character, Integer> cardMap = new HashMap<>();

    public static void main(String[] args) {
        Day7 day7 = new Day7();
        day7.problem2();
    }

    public Day7() {
        try {
            input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day7/input.txt"));
        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }
        fillCardMapProblem2(cardMap);
    }

    public void problem2() {
        List<Hand> hands = new ArrayList<>();
        for (String line : input) {
            String[] splitLine = line.split(" ");
            Hand hand = new Hand(splitLine[0], Integer.parseInt(splitLine[1]));
            hands.add(hand);
        }
        Collections.sort(hands);

        int rank = 1;
        int result = 0;

        for (Hand hand : hands) {
            result += rank * hand.getBet();
            rank += 1;
            hand.setResult(rank);
        }

        hands.stream().map(h -> h.toString()).forEach(System.out::println);
        System.out.printf("Result: %d", result);
    }

    public void problem1() {
        List<Hand> hands = new ArrayList<>();
        for (String line : input) {
            String[] splitLine = line.split(" ");
            Hand hand = new Hand(splitLine[0], Integer.parseInt(splitLine[1]));
            hands.add(hand);
        }
        Collections.sort(hands);

        int rank = 1;
        int result = 0;

        for (Hand hand : hands) {
            result += rank * hand.getBet();
            rank += 1;
        }

        System.out.printf("Result: %d", result);
    }

    private void fillCardMap(Map<Character, Integer> map) {
        // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
        map.put('A', 14);
        map.put('K', 13);
        map.put('Q', 12);
        map.put('J', 11);
        map.put('T', 10);

        for (int i = 9; i > 1; i--) {
            map.put(Character.forDigit(i, 10), i);
        }
    }

    private void fillCardMapProblem2(Map<Character, Integer> map) {
        // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
        map.put('A', 14);
        map.put('K', 13);
        map.put('Q', 12);
        map.put('J', 1);
        map.put('T', 10);

        for (int i = 9; i > 1; i--) {
            map.put(Character.forDigit(i, 10), i);
        }
    }

    static Map<Character, Integer> getMap() {
        return cardMap;
    }
}
