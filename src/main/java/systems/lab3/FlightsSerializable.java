package systems.lab3;


import java.io.Serializable;

public class FlightsSerializable implements Serializable {
    private float delay;
    private float latePercent;
    private float cancelledPercent;

    private int count;

    public float getLateTime() {
        return delay;
    }

    public float isLate() {
        return latePercent;
    }

    public float isCancelled() {
        return cancelledPercent;
    }

    public int getCount() {
        return count;
    }

    public FlightsSerializable(float delay, float isCancelled, int count) {
        this.delay = delay;
        latePercent = (delay == 0) ? 0 : 100;
        this.cancelledPercent = isCancelled * 100;
        this.count = count;
    }

    @Override
    public String toString() {
        return "FlightSerializable{" +
                "delay=" + delay +
                ", isLate=" + latePercent +
                ", isCancelled=" + cancelledPercent +
                '}';
    }
}
