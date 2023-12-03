package pt.sergioigreja.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    private File input;

    public Day3() {
        input = new File("src/main/java/pt/sergioigreja/day3/input.txt");
    }

    public void problem1() {
        int result = 0;
        try {
            Scanner scanner = new Scanner(input);
            List<Part> prevParts = new ArrayList<>();
            while (scanner.hasNextLine()) {
                List<Part> currentParts = new ArrayList<>();
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        int number = Character.getNumericValue(c);
                        int j = i + 1;
                        for (; j < line.length(); j++) {
                            c = line.charAt(j);
                            if (Character.isDigit(c)) {
                                number = number * 10 + Character.getNumericValue(c);
                            } else {
                                break;
                            }
                        }
                        Part part = new Part(number, i, j - 1);
                        i = j - 1;
                        if (!currentParts.isEmpty()) {
                            Part prevPart = currentParts.get(currentParts.size() - 1);
                            if (part.isAdjacent(prevPart)) {
                                result += part.number();
                            }
                        }
                        currentParts.add(part);
                        for (Part prevPart : prevParts) {
                            if (part.isAdjacent(prevPart)) {
                                result += part.number();
                            }
                        }
                    } else if (c != '.') {
                        Part part = new Part(i);
                        if (!currentParts.isEmpty()) {
                            Part prevPart = currentParts.get(currentParts.size() - 1);
                            if (part.isAdjacent(prevPart)) {
                                result += prevPart.number();
                            }
                        }
                        currentParts.add(part);
                        for (Part prevPart : prevParts) {
                            if (part.isAdjacent(prevPart)) {
                                result += prevPart.number();
                            }
                        }
                    }
                }
                prevParts = currentParts;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }

        System.out.printf("The sum of the part number is %d", result);
    }

    public void problem2() {
        int result = 0;
        try {
            Scanner scanner = new Scanner(input);
            List<Part> prevParts = new ArrayList<>();
            while (scanner.hasNextLine()) {
                List<Part> currentParts = new ArrayList<>();
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        int number = Character.getNumericValue(c);
                        int j = i + 1;
                        for (; j < line.length(); j++) {
                            c = line.charAt(j);
                            if (Character.isDigit(c)) {
                                number = number * 10 + Character.getNumericValue(c);
                            } else {
                                break;
                            }
                        }
                        Part part = new Part(number, i, j - 1);
                        i = j - 1;
                        if (!currentParts.isEmpty()) {
                            Part prevPart = currentParts.get(currentParts.size() - 1);
                            if (part.isAdjacent(prevPart)) {
                                if (prevPart.isGear()) {
                                    prevPart.addAdjacentToGear(part);
                                }
                            }
                        }
                        currentParts.add(part);
                        for (Part prevPart : prevParts) {
                            if (part.isAdjacent(prevPart)) {
                                if (prevPart.isGear()) {
                                    prevPart.addAdjacentToGear(part);
                                }
                            }
                        }
                    } else if (c == '*') {
                        Part part = new Part(0, i);
                        if (!currentParts.isEmpty()) {
                            Part prevPart = currentParts.get(currentParts.size() - 1);
                            if (prevPart.isPartNumber() && part.isAdjacent(prevPart)) {
                                part.addAdjacentToGear(prevPart);
                            }
                        }
                        currentParts.add(part);
                        for (Part prevPart : prevParts) {
                            if (prevPart.isPartNumber() && part.isAdjacent(prevPart)) {
                                part.addAdjacentToGear(prevPart);
                            }
                        }
                    }
                }
                for (Part prevPart : prevParts) {
                    if (prevPart.isGear() && prevPart.getAdjacents().size() == 2) {
                        result += prevPart.getAdjacents().get(0).number() * prevPart.getAdjacents().get(1).number();
                    }
                }
                prevParts = currentParts;
            }
            for (Part prevPart : prevParts) {
                if (prevPart.isGear() && prevPart.getAdjacents().size() == 2) {
                    result += prevPart.getAdjacents().get(0).number() * prevPart.getAdjacents().get(1).number();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }

        System.out.printf("The sum of the part number is %d", result);
    }
}
