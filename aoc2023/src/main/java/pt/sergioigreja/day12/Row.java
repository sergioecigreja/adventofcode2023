package pt.sergioigreja.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {
    private String springs;
    private List<Integer> damagedGroups;
    private Map<State, Long> cache;

    public Row(String springs, List<Integer> damagedGroups) {
        this.springs = springs;
        this.damagedGroups = damagedGroups;
        this.cache = new HashMap<>();
    }

    public long countDifferentWays() {
        System.out.println(this.damagedGroups);
        return helper(0, 0, 0);
    }

    private long helper(int currentIndex, int groupIndex, int groupCount) {

        State state = new State(currentIndex, groupIndex, groupCount);
        if (this.cache.containsKey(state)) {
            return this.cache.get(state);
        }

        if (currentIndex == this.springs.length()) {
            if (groupIndex == this.damagedGroups.size()) {
                if (groupCount == 0) {
                    return 1L;
                }
            }
            if (groupIndex == this.damagedGroups.size() - 1) {
                if (groupCount == this.damagedGroups.get(groupIndex)) {
                    return 1L;
                }
            }
            return 0L;
        }

        long result = 0;
        char current = this.springs.charAt(currentIndex);

        if (current == '.' || current == '?') {
            if (groupIndex < this.damagedGroups.size() && groupCount == this.damagedGroups.get(groupIndex)) {
                result += helper(currentIndex + 1, groupIndex + 1, 0);
            }
            if (groupCount == 0) {
                result += helper(currentIndex + 1, groupIndex, groupCount);
            }
        }

        if (current == '#' || current == '?') {
            result += helper(currentIndex + 1, groupIndex, groupCount + 1);
        }

        this.cache.put(state, result);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Row %s | ".concat(this.damagedGroups.toString()), this.springs);
    }

    final class State {
        private int pos;
        private int group;
        private int groupLen;

        public State(int pos, int group, int groupLen) {
            this.pos = pos;
            this.group = group;
            this.groupLen = groupLen;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + group;
            result = prime * result + groupLen;
            result = prime * result + pos;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            State other = (State) obj;
            return pos == other.pos && group == other.group && groupLen == other.groupLen;
        }
    }
}
