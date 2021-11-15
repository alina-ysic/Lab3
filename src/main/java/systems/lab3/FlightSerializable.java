package systems.lab3;


import java.io.Serializable;

public class FlightSerializable implements Serializable {
    private float delay;
    private boolean isLate;
    private boolean isCancelled;

    public float getLateTime() {
        return delay;
    }

    public boolean isLate() {
        return isLate;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public FlightSerializable(float delay, boolean isCancelled) {
        this.delay = delay;
        if (delay != 0) isLate = true;
        this.isCancelled = isCancelled;
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
