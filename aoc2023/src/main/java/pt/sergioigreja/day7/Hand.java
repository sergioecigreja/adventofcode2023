package pt.sergioigreja.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Hand implements Comparable<Hand> {

    private String cards;
    private int bet;
    private int type;
    private int result;

    public Hand(String cards, int bet) {
        this.cards = cards;
        this.bet = bet;
        this.type = processProblem2(this.cards);
    }

    private int process(String cards) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : cards.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int result = 0;
        for (Entry<Character, Integer> entry : map.entrySet()) {
            result += (int) Math.pow(entry.getValue(), entry.getValue());
        }

        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    private int processProblem2(String cards) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : cards.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int jokerCount = map.getOrDefault('J', 0);
        int result = 0;

        if (jokerCount == 5) {
            return (int) Math.pow(5, 5);
        } else if (jokerCount == 0) {
            for (Entry<Character, Integer> entry : map.entrySet()) {
                result += (int) Math.pow(entry.getValue(), entry.getValue());
            }
        } else {
            int maxCount = 0;
            char maxKey = ' ';
            for (Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() > maxCount && entry.getKey() != 'J') {
                    maxCount = entry.getValue();
                    maxKey = entry.getKey();
                } else if (entry.getValue() == maxCount && entry.getKey() != 'J') {
                    maxKey = Day7.cardMap.get(maxKey) > Day7.cardMap.get(entry.getKey()) ? maxKey : entry.getKey();
                }
            }

            map.put(maxKey, map.get(maxKey) + jokerCount);

            for (Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getKey() != 'J') {
                    result += (int) Math.pow(entry.getValue(), entry.getValue());
                }
            }

        }

        return result;
    }

    public String getCards() {
        return this.cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public int getBet() {
        return this.bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Hand: %s - Bet: %d - Type: %d - Result: %d", this.cards, this.bet, this.type,
                this.result);
    }

    @Override
    public int compareTo(Hand o) {
        if (this.type == o.type) {
            for (int i = 0; i < 5; i++) {
                if (this.cards.charAt(i) != o.getCards().charAt(i)) {
                    return Day7.cardMap.get(this.cards.charAt(i)).compareTo(Day7.cardMap.get(o.getCards().charAt(i)));
                }
            }
        } else if (this.type > o.type) {
            return 1;
        } else {
            return -1;
        }

        return 0;
    }

}
