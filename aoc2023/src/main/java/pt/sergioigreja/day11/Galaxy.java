package pt.sergioigreja.day11;

public class Galaxy {
    private int line;
    private int column;
    private int number;

    public Galaxy(int line, int column, int number) {
        this.line = line;
        this.column = column;
        this.number = number;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Galaxy [line=" + line + ", column=" + column + ", number=" + number + "]";
    }

}
