package pt.sergioigreja.day7;

import java.util.Comparator;

public class CardComparator implements Comparator<Character> {

    @Override
    public int compare(Character arg0, Character arg1) {
        if (arg0 == arg1) {
            return 0;
        }

        return Day7.cardMap.get(arg0).compareTo(Day7.cardMap.get(arg1));
    }

}
