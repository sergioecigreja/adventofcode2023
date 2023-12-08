package pt.sergioigreja.day8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {
    private String value;
    private Node left;
    private Node right;
    private Map<Node, EndPoint> endNodes;

    public Node() {
    }

    public Node(String value) {
        this.value = value;
    }

    public Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Map<Node, EndPoint> getEndNodes() {
        return this.endNodes;
    }

    public void setEndNodes(Map<Node, EndPoint> endNodes) {
        this.endNodes = endNodes;
    }

    public void calculateRepeatingEndNodes(String instructions) {
        this.endNodes = new HashMap<>();
        Map<Node, Integer> map = new HashMap<>();
        final String regexEnd = "\\w*Z+\\w*";
        final Pattern patternEnd = Pattern.compile(regexEnd, Pattern.MULTILINE);

        int i = 0;
        Node next = this;
        int totalCounter = 0;
        int initialFind = 0;
        do {
            if (i == instructions.length()) {
                i = 0;
            }
            Matcher matcher = patternEnd.matcher(next.value);
            if (matcher.matches()) {
                if (map.getOrDefault(next, 0) == 1)
                    break;
                map.put(next, 1);
                initialFind = totalCounter;
            }
            next = instructions.charAt(i) == 'L' ? next.left : next.right;
            i += 1;
            totalCounter += 1;
        } while (true);
        this.endNodes.put(next, new EndPoint(initialFind, totalCounter - initialFind));
    }

    @Override
    public String toString() {
        if (this.getEndNodes() != null) {
            return String.format("Value = %s - Left = %s - Right = %s\n".concat(this.endNodes.toString()).concat("\n"),
                    this.value, this.left.getValue(),
                    this.right.getValue());
        }
        return String.format("Value = %s - Left = %s - Right = %s\n", this.value, this.left.getValue(),
                this.right.getValue());
    }

}
