package ua.shield.enumer;

/**
 * Created by sa on 04.09.17.
 */
public enum DateStrategy {
    ONCE("Once"),
    EVERYHOUR("Every hour"),
    EVERYDAY("Every day"),
    EVERYWEEK("Every week"),
    SPECIALPERIOD("Special period");

    private final String name;
    DateStrategy(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}