package pt.sergioigreja;

import pt.sergioigreja.day1.Day1;

public class Application {

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        int result = day1.sumAllCalibrationValuesPart2();
        System.out.printf("Sum of calibration values: %d", result);
    }
}
