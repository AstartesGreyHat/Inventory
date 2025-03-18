package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://" + DatabaseConfig.getHost() + ":" + DatabaseConfig.getPort() + "/" + DatabaseConfig.getDatabase()
                + "?useSSL=false&serverTimezone=UTC";

        return DriverManager.getConnection(url, DatabaseConfig.getUser(), DatabaseConfig.getPassword());
    }

    public static void showInventory() {
        String query = "SELECT id, nombre, cantidad, precio FROM inventario"; // Ajusta el nombre de la tabla y columnas

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n📦 Inventario Disponible:\n");

            // Encabezados de la tabla
            System.out.printf("%-5s | %-20s | %-10s | %-10s%n", "ID", "Producto", "Cantidad", "Precio");
            System.out.println("-----------------------------------------------------------");

            // Mostrar los datos de la tabla
            while (rs.next()) {
                System.out.printf("%-5d | %-20s | %-10d | $%-9.2f%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"));
            }
            System.out.println("-----------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("❌ Error al cargar el inventario: " + e.getMessage());
        }
    }
    public static boolean testConnection(String url, String user, String password) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            return conn != null;
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
            return false;
        }
    }
}
