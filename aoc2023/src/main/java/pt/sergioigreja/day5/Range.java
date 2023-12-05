package pt.sergioigreja.day5;

import java.util.HashMap;
import java.util.Map;

public class Range {
    private double startingValue;
    private double endingValue;
    private double destinationStart;
    // private Map<Double, Double> map;

    public Range(double destinationStart, double startingValue, double rangeLength) {
        this.destinationStart = destinationStart;
        this.startingValue = startingValue;
        this.endingValue = startingValue + rangeLength;
        // this.map = new HashMap<>();
    }

    public Range() {

    }

    /*
     * public Map<Double, Double> getMap() {
     * return this.map;
     * }
     */

    public boolean inRange(double value) {
        return this.startingValue <= value && value <= this.endingValue;
    }

    public double getStartingValue() {
        return this.startingValue;
    }

    public void setStartingValue(double startingValue) {
        this.startingValue = startingValue;
    }

    public double getEndingValue() {
        return this.endingValue;
    }

    public void setEndingValue(double endingValue) {
        this.endingValue = endingValue;
    }

    public double getDestinationStart() {
        return this.destinationStart;
    }

    public void setDestinationStart(double destinationStart) {
        this.destinationStart = destinationStart;
    }

}
