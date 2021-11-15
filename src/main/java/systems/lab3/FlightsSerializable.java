package systems.lab3;


import java.io.Serializable;

public class FlightsSerializable implements Serializable {
    private String nameInAirport;
    private String nameOutAirport;
    private float delay;
    private float latePercent;
    private float cancelledPercent;

    private int count;

    public float getDelay() {
        return delay;
    }

    public float getLatePercent() {
        return latePercent;
    }

    public float getCancelledPercent() {
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

    public FlightsSerializable(float delay, float cancelledPercent, float latePercent, int count) {
        this.delay = delay;
        this.latePercent = latePercent;
        this.cancelledPercent = cancelledPercent;
        this.count = count;
    }

    public void setNameInAirport(String nameInAirport) {
        this.nameInAirport = nameInAirport;
    }

    public void setNameOutAirport(String nameOutAirport) {
        this.nameOutAirport = nameOutAirport;
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
