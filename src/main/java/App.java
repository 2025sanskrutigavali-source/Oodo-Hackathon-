public class App {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   TRANSOPS LOGISTICS SYSTEM - INTEGRATION TEST   ");
        System.out.println("==================================================");

        // Target test IDs matching our schema seeds
        int vehicleId = 1; 
        int driverId = 1; 
        double routeDistance = 150.5;

        // TEST 1: Safe payload weight assignment
        System.out.println("\n[TEST 1] Attempting a safe shipment of 5,000 kg...");
        System.out.println("--------------------------------------------------");
        TripManager.dispatchTrip("Mumbai", "Pune", vehicleId, driverId, 5000.00, routeDistance);

        System.out.println("\n==================================================");

        // TEST 2: Overloaded payload weight allocation
        System.out.println("\n[TEST 2] Attempting an overloaded shipment of 9,000 kg...");
        System.out.println("--------------------------------------------------");
        TripManager.dispatchTrip("Mumbai", "Nashik", vehicleId, driverId, 9000.00, routeDistance);
        
        System.out.println("\n==================================================");
        System.out.println("              TESTING CYCLE COMPLETE              ");
        System.out.println("==================================================");
    }
}