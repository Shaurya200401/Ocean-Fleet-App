import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OceanFleetApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VesselUtil vesselUtil = new VesselUtil();

        // System.out.println("Enter number of vessels:");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            // System.out.println("Enter vessel details (id:name:speed:type):");
            String line = sc.nextLine();
            Vessel v = Vessel.parseVessel(line);
            if (v != null) {
                vesselUtil.addVesselPerformance(v);
            }
        }

        // System.out.println("Enter vessel ID to search:");
        String searchId = sc.nextLine();
        Vessel found = vesselUtil.getVesselById(searchId);

        if (found != null) {
            displayVessel(found);
        } else {
            System.out.println("Vessel Id " + searchId + " not found.");
        }

        List<Vessel> highPerf = vesselUtil.getHighPerformanceVessels();
        for (Vessel v : highPerf) {
            displayVessel(v);
        }

        sc.close();
    }

    private static void displayVessel(Vessel v) {
        System.out.println(v.getVesselId() + " | " + v.getVesselName() + " | " + v.getVesselType()
                + " | " + v.getAverageSpeed() + " knots");
    }

    // Static Inner Class: Vessel
    static class Vessel {
        private String vesselId;
        private String vesselName;
        private double averageSpeed;
        private String vesselType;

        public Vessel() {
        }

        public Vessel(String vesselId, String vesselName, double averageSpeed, String vesselType) {
            this.vesselId = vesselId;
            this.vesselName = vesselName;
            this.averageSpeed = averageSpeed;
            this.vesselType = vesselType;
        }

        public String getVesselId() {
            return vesselId;
        }

        public void setVesselId(String vesselId) {
            this.vesselId = vesselId;
        }

        public String getVesselName() {
            return vesselName;
        }

        public void setVesselName(String vesselName) {
            this.vesselName = vesselName;
        }

        public double getAverageSpeed() {
            return averageSpeed;
        }

        public void setAverageSpeed(double averageSpeed) {
            this.averageSpeed = averageSpeed;
        }

        public String getVesselType() {
            return vesselType;
        }

        public void setVesselType(String vesselType) {
            this.vesselType = vesselType;
        }

        public static Vessel parseVessel(String vesselData) {
            String[] parts = vesselData.split(":");
            if (parts.length == 4) {
                return new Vessel(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
            }
            return null;
        }
    }

    // Static Inner Class: VesselUtil
    static class VesselUtil {
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
}
