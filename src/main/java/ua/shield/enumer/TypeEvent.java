package ua.shield.enumer;

/**
 * Created by sa on 04.09.17.
 */
public enum TypeEvent{
    ONCE(0),RANGE(1);
    private final int status;

    TypeEvent(int status) {
        this.status = status;
    }

    public int toInt() {
        return status;
    }
}