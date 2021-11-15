package systems.lab3;


import java.io.Serializable;

public class FlightSerializable implements Serializable {
    private Integer lateTime;
    private boolean isLate;
    private boolean isCancelled;

    public Integer getLateTime() {
        return lateTime;
    }

    public boolean isLate() {
        return isLate;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
