import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripManager {
    public static void dispatchTrip(String source, String dest, int vehicleId, int driverId, double cargoWeight, double distance) {
        // 1. Validate Driver Existence
        if (!DriverManager.driverExists(driverId)) {
            System.out.println("❌ DISPATCH REJECTED: Driver ID " + driverId + " does not exist in system registry!");
            return;
        }

        // 2. Validate Vehicle Existence and Capacity
        double maxCapacity = VehicleManager.getVehicleMaxCapacity(vehicleId);
        if (maxCapacity == -1) {
            System.out.println("❌ DISPATCH REJECTED: Vehicle ID " + vehicleId + " does not exist in system registry!");
            return;
        }

        if (cargoWeight > maxCapacity) {
            String vehicleName = VehicleManager.getVehicleName(vehicleId);
            System.out.println("❌ DISPATCH REJECTED: Cargo weight (" + cargoWeight + " kg) exceeds the maximum capacity of " + vehicleName + " (" + maxCapacity + " kg)!");
            return;
        }

        // 3. Process Trip Dispatch and Database Log
        String insertQuery = "INSERT INTO trips (source_location, destination_location, vehicle_id, driver_id, cargo_weight, route_distance) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            
            stmt.setString(1, source);
            stmt.setString(2, dest);
            stmt.setInt(3, vehicleId);
            stmt.setInt(4, driverId);
            stmt.setDouble(5, cargoWeight);
            stmt.setDouble(6, distance);
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ TRIP DISPATCHED SUCCESSFULLY! Safe travels from " + source + " to " + dest + ".");
            }
        } catch (SQLException e) {
            System.err.println("❌ CRITICAL: Database write failed during dispatch log! " + e.getMessage());
        }
    }
}