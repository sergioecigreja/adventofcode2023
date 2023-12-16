package pt.sergioigreja.day15;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Logger;

public class Day15 {
    private String input;
    private String[] sequence;

    // 212763
    public Day15() {
        try {
            this.input = Files.readString(Paths.get("src/main/java/pt/sergioigreja/day15/input"));

            this.sequence = this.input.split(",");

            System.out.println(Arrays.toString(this.sequence));
        } catch (Exception e) {
            Logger.getGlobal().warning(e.toString());
        }
    }

    public void problem1() {
        int result = 0;

        for (String step : sequence) {
            result += hash(step);
        }

        System.out.println(result);
    }

    public void problem2() {
        ListNode[] boxes = new ListNode[256];
        boolean dash = false;

        for (String step : sequence) {
            String[] split = step.split("=");
            if (split.length == 1) {
                dash = true;
            }
            if (!dash) {
                int index = hash(split[0]);
                ListNode listNode = new ListNode(split[0], Integer.valueOf(split[1]), null, null);

                if (boxes[index] == null) {
                    boxes[index] = listNode;
                } else {
                    ListNode root = boxes[index];
                    while (root.getNext() != null) {
                        if (root.getLabel().equals(listNode.getLabel())) {
                            root.setValue(listNode.getValue());
                            break;
                        }
                        root = root.getNext();
                    }
                    if (!root.getLabel().equals(listNode.getLabel())) {
                        root.setNext(listNode);
                        listNode.setPrev(root);
                    } else {
                        root.setValue(listNode.getValue());
                    }
                }
            } else {
                step = step.substring(0, step.length() - 1);
                int index = hash(step);
                ListNode root = boxes[index];
                while (root != null) {
                    if (root.getLabel().equals(step)) {
                        ListNode prev = root.getPrev();
                        ListNode next = root.getNext();
                        if (prev == null) {
                            boxes[index] = next;
                        } else {
                            prev.setNext(next);
                        }
                        if (next != null) {
                            next.setPrev(prev);
                        }
                        break;
                    }
                    root = root.getNext();
                }
            }
            dash = false;
        }

        int result = 0;
        for (int i = 0; i < boxes.length; i++) {
            ListNode root = boxes[i];
            int index = 1;

            while (root != null) {
                result += (i + 1) * index * root.getValue();
                index += 1;
                root = root.getNext();
            }
        }

        System.out.println(result);
    }

    public int hash(String input) {
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            result += input.charAt(i);
            result *= 17;
            result = result % 256;
        }

        return result;
    }

    public static void main(String[] args) {
        Day15 day15 = new Day15();
        day15.problem2();
    }
}
