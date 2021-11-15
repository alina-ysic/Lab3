package systems.lab3;


import java.io.Serializable;

public class FlightsSerializable implements Serializable {
    private float delay;
    private float isLate;
    private float isCancelled;

    private int count;

    public float getLateTime() {
        return delay;
    }

    public float isLate() {
        return isLate;
    }

    public float isCancelled() {
        return isCancelled;
    }

    public int getCount() {
        return count;
    }

    public FlightsSerializable(float delay, float isCancelled, int count) {
        this.delay = delay;
        isLate = (delay == 0) ? 0 : 1;
        this.isCancelled = isCancelled;
        this.count = count;
    }

    @Override
    public String toString() {
        return "FlightSerializable{" +
                "delay=" + delay +
                ", isLate=" + isLate +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
