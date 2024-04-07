package com.flightbooking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private String url = "jdbc:db2://localhost:50000/YOUR_DB_NAME";
    private String user = "db2user";
    private String password = "db2password";
    
    public DatabaseManager() {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void insertBooking(String bookingDetails) {
        // Example SQL; your table structure will vary
        String sql = "INSERT INTO bookings (details) VALUES (?)";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, bookingDetails);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
