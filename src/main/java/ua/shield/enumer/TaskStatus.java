package ua.shield.enumer;

/**
 * Created by sa on 01.09.17.
 */
public enum TaskStatus {
   READY(0),IN_WORK(1),DONE(2);
    private final int status;

    TaskStatus(int status) {
        this.status = status;
    }

    public int toInt() {
        return status;
    }
}
