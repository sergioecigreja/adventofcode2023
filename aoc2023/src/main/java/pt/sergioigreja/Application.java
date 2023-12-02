package pt.sergioigreja;

import pt.sergioigreja.day1.Day1;
import pt.sergioigreja.day2.Day2;

public class Application {

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        int result = day2.sumValidGamesId(12, 13, 14);
        System.out.printf("Result: %d", result);
    }
}
