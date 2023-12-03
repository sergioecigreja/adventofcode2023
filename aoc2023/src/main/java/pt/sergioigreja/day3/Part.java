package pt.sergioigreja.day3;

import java.util.ArrayList;
import java.util.List;

public class Part {
    private int number;
    private int startsAt;
    private int endsAt;
    private List<Part> adjacents;

    public Part(int startsAt) {
        this.number = -1;
        this.startsAt = startsAt;
        this.endsAt = -1;
    }

    // Gear
    public Part(int number, int startsAt) {
        this.number = 0;
        this.startsAt = startsAt;
        this.endsAt = 0;
    }

    public Part(int number, int startsAt, int endsAt) {
        this.number = number;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public boolean isAdjacent(Part part) {
        // is number
        if (part.isPartNumber()) {
            if (this.isPartNumber()) {
                return false;
            }
            if (this.startsAt >= part.startsAt && this.startsAt <= part.endsAt()) {
                return true;
            }
            if (this.startsAt == part.startsAt() - 1 || this.startsAt == part.endsAt() + 1) {
                return true;
            }
        } else {
            // is symbol
            if (!this.isPartNumber()) {
                return false;
            }
            if (part.startsAt() >= this.startsAt && part.startsAt() <= this.endsAt) {
                return true;
            }
            if (this.endsAt + 1 == part.startsAt() || this.startsAt - 1 == part.startsAt()) {
                return true;
            }
        }
        return false;
    }

    public List<Part> getAdjacents() {
        if (isGear()) {
            if (this.adjacents == null) {
                this.adjacents = new ArrayList<>();
            }
            return this.adjacents;
        }
        return null;
    }

    public boolean isPartNumber() {
        return this.number > 0;
    }

    public boolean isGear() {
        return this.number == 0;
    }

    public void addAdjacentToGear(Part adjacent) {
        if (this.isGear() && adjacent.isPartNumber()) {
            if (this.adjacents == null) {
                this.adjacents = new ArrayList<>();
            }
            this.adjacents.add(adjacent);
        }
    }

    public int number() {
        return this.number;
    }

    public int startsAt() {
        return this.startsAt;
    }

    public int endsAt() {
        return this.endsAt;
    }
}
