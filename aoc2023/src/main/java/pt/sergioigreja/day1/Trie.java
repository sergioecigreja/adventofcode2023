package pt.sergioigreja.day1;

import java.util.HashMap;
import java.util.List;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(List<String> words) {
        HashMap<Character, TrieNode> children = root.getChildren();
        for (int i = 1; i - 1 < words.size(); i++) {
            String word = words.get(i - 1);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                TrieNode node;
                if (children.containsKey(c)) {
                    node = children.get(c);
                } else {
                    node = new TrieNode(c);
                    children.put(c, node);
                }
                children = node.getChildren();

                if (j == word.length() - 1) {
                    node.setLeaf(i);
                }
            }
            children = this.root.getChildren();
        }

    }

    public TrieNode getRoot() {
        return this.root;
    }

    public int search(String word) {
        HashMap<Character, TrieNode> children = root.getChildren();

        TrieNode node = null;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (children.containsKey(c)) {
                node = children.get(c);
                children = node.getChildren();
            } else {
                node = null;
                break;
            }
        }

        if (node != null) {
            return node.isLeaf();
        }
        return -1;
    }

}