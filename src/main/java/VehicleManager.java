import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleManager {
    public static double getVehicleMaxCapacity(int vehicleId) {
        String query = "SELECT max_capacity FROM vehicles WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("max_capacity");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Error checking vehicle capacity: " + e.getMessage());
        }
        return -1; // Indicates vehicle not found or error occurred
    }

    public static String getVehicleName(int vehicleId) {
        String query = "SELECT name FROM vehicles WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Error fetching vehicle name: " + e.getMessage());
        }
        return "Unknown Vehicle";
    }
}