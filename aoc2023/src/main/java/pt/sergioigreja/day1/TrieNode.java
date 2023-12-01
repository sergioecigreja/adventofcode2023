package pt.sergioigreja.day1;

import java.util.HashMap;

public class TrieNode {
    private char c;
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private int value;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
        this.value = -1;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public int isLeaf() {
        return this.value;
    }

    public void setLeaf(int value) {
        this.value = value;
    }
}