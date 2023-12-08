package pt.sergioigreja.day8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {
    private List<String> input;
    String instructions;
    Map<String, Node> map = new HashMap<>();

    public static void main(String[] args) {
        Day8 day8 = new Day8();

        day8.problem2();
    }

    private void problem2() {
        List<Node> nodes = new ArrayList<>();
        final String regex = "\\w*A+\\w*";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

        for (Entry<String, Node> entry : this.map.entrySet()) {
            Matcher matcher = pattern.matcher(entry.getKey());
            if (matcher.matches()) {
                nodes.add(entry.getValue());
                entry.getValue().calculateRepeatingEndNodes(this.instructions);
            }
        }

        int[] stepSToEndOfEachNode = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            for (Entry<Node, EndPoint> entry : nodes.get(i).getEndNodes().entrySet()) {
                stepSToEndOfEachNode[i] = entry.getValue().step();
            }
        }

        long result = lcm_of_array_elements(stepSToEndOfEachNode);

        System.out.println(result);

    }

    public static long lcm_of_array_elements(int[] element_array) {
        long lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < element_array.length; i++) {

                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.

                if (element_array[i] == 0) {
                    return 0;
                } else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            } else {
                divisor++;
            }

            // Check if all element_array is 1 indicate
            // we found all factors and terminate while loop.
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }

    private void problem1() {
        // this.map.forEach((value, node) -> System.out.println(node.toString()));

        Node node = map.get("AAA");
        int counter = 0;
        int i = 0;
        do {
            if (i == instructions.length()) {
                i = 0;
            }
            if (instructions.charAt(i) == 'L') {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
            counter += 1;
            i += 1;
        } while (!node.equals(map.get("ZZZ")));

        System.out.printf("Result is %d", counter);
    }

    public Day8() {
        try {
            this.input = Files.readAllLines(Paths.get("src/main/java/pt/sergioigreja/day8/input"));
            StringBuilder sb = new StringBuilder();
            final String regex = "(\\w+)\\s=\\s\\((\\w+),\\s(\\w+)\\)";
            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

            boolean hasMoreInstructions = true;
            for (String line : input) {
                if (line.isEmpty()) {
                    instructions = sb.toString();
                    hasMoreInstructions = false;
                } else if (hasMoreInstructions) {
                    sb.append(line);
                } else {
                    Matcher matcher = pattern.matcher(line.trim());
                    if (matcher.matches()) {
                        String parentString = matcher.group(1);
                        String leftString = matcher.group(2);
                        String rightString = matcher.group(3);

                        Node parent = map.getOrDefault(parentString, new Node(parentString));
                        map.put(parent.getValue(), parent);

                        Node left = map.get(leftString);
                        Node right = map.get(rightString);
                        if (left == null) {
                            left = new Node(leftString);
                            map.put(leftString, left);
                        }
                        if (right == null) {
                            right = new Node(rightString);
                            map.put(rightString, right);
                        }
                        parent.setLeft(map.get(leftString));
                        parent.setRight(map.get(rightString));
                    }
                }
            }

        } catch (Exception e) {
            Logger.getGlobal().warning(e.toString());
        }
    }

}
