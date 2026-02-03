import java.util.ArrayList;
import java.util.List;

public class VesselUtil {
    private List<Vessel> vesselList;

    public VesselUtil() {
        this.vesselList = new ArrayList<>();
    }

    public void addVesselPerformance(Vessel vessel) {
        if (vessel != null) {
            this.vesselList.add(vessel);
        }
    }

    public Vessel getVesselById(String vesselId) {
        for (Vessel v : vesselList) {
            if (v.getVesselId().equals(vesselId)) {
                return v;
            }
        }
        return null;
    }

    public List<Vessel> getHighPerformanceVessels() {
        List<Vessel> highPerformanceVessels = new ArrayList<>();
        if (vesselList.isEmpty()) {
            return highPerformanceVessels;
        }

        double maxSpeed = 0.0;
        for (Vessel v : vesselList) {
            if (v.getAverageSpeed() > maxSpeed) {
                maxSpeed = v.getAverageSpeed();
            }
        }

        for (Vessel v : vesselList) {
            if (v.getAverageSpeed() == maxSpeed) {
                highPerformanceVessels.add(v);
            }
        }
        return highPerformanceVessels;
    }
}
