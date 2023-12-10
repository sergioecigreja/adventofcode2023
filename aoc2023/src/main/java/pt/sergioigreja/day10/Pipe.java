package pt.sergioigreja.day10;

public class Pipe {
    private Pipe up;
    private Pipe down;
    private Pipe left;
    private Pipe right;
    private char value;
    private int numConnections;

    public Pipe(char value) {
        this.value = value;
        numConnections = 0;
    }

    public Pipe getUp() {
        return up;
    }

    public void setUp(Pipe up) {
        if (canConnectUp(up)) {
            this.up = up;
            numConnections += 1;
        }
    }

    public Pipe getDown() {
        return down;
    }

    public void setDown(Pipe down) {
        if (canConnectDown(down)) {
            this.down = down;
            numConnections += 1;
        }
    }

    public Pipe getLeft() {
        return left;
    }

    public void setLeft(Pipe left) {
        if (canConnectLeft(left)) {
            this.left = left;
            numConnections += 1;
        }
    }

    public Pipe getRight() {
        return right;
    }

    public void setRight(Pipe right) {
        if (canConnectRight(right)) {
            this.right = right;
            numConnections += 1;
        }
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isRelevant() {
        return numConnections > 1;
    }

    public Pipe next(Pipe previous) {
        if (this.down != null && !this.down.equals(previous)) {
            return this.down;
        }
        if (this.up != null && !this.up.equals(previous)) {
            return this.up;
        }
        if (this.left != null && !this.left.equals(previous)) {
            return this.left;
        }
        return this.right;
    }

    public boolean canConnectDown(Pipe other) {
        if (other == null) {
            return false;
        }

        if (this.value == '-' || other.value == '-') {
            return false;
        }

        switch (this.value) {
            case 'L', 'J', '-':
                return false;
            default:
                switch (other.value) {
                    case '-', '7', 'F':
                        return false;
                    default:
                        return true;
                }
        }
    }

    public boolean canConnectUp(Pipe other) {
        if (other == null) {
            return false;
        }
        if (this.value == '-' || other.value == '-') {
            return false;
        }

        switch (this.value) {
            case '-', '7', 'F':
                return false;
            default:
                switch (other.value) {
                    case 'L', 'J', '-':
                        return false;
                    default:
                        return true;
                }
        }
    }

    public boolean canConnectLeft(Pipe other) {
        if (other == null) {
            return false;
        }

        if (this.value == '|' || other.value == '|') {
            return false;
        }

        switch (this.value) {
            case '|', 'F', 'L':
                return false;
            default:
                switch (other.value) {
                    case '|', 'J', '7':
                        return false;
                    default:
                        return true;
                }
        }
    }

    public boolean canConnectRight(Pipe other) {
        if (other == null) {
            return false;
        }

        if (this.value == '|' || other.value == '|') {
            return false;
        }

        switch (this.value) {
            case '|', 'J', '7':
                return false;
            default:
                switch (other.value) {
                    case '|', 'F', 'L':
                        return false;
                    default:
                        return true;
                }
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
