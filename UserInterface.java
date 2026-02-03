import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VesselUtil vesselUtil = new VesselUtil();

        // System.out.println("Enter number of vessels:");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            // System.out.println("Enter vessel details (id:name:speed:type):");
            String line = sc.nextLine();
            String[] parts = line.split(":");
            // Assuming valid input as per UC5 status (validation is in UC7)
            if (parts.length == 4) {
                Vessel v = new Vessel(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
                vesselUtil.addVesselPerformance(v);
            }
        }

        // System.out.println("Enter vessel ID to search:");
        String searchId = sc.nextLine();
        Vessel found = vesselUtil.getVesselById(searchId);

        if (found != null) {
            System.out.println(found.getVesselId() + " | " + found.getVesselName() + " | " + found.getVesselType()
                    + " | " + found.getAverageSpeed() + " knots");
        } else {
            System.out.println("Vessel Id " + searchId + " not found.");
        }

        List<Vessel> highPerf = vesselUtil.getHighPerformanceVessels();
        for (Vessel v : highPerf) {
            System.out.println(v.getVesselId() + " | " + v.getVesselName() + " | " + v.getVesselType() + " | "
                    + v.getAverageSpeed() + " knots");
        }

        sc.close();
    }
}
