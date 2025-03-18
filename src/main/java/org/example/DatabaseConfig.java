package org.example;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import org.example.MySQLConnection;

public class DatabaseConfig {
    private static String host = "localhost";
    private static String port = "3306";
    private static String user = "root";
    private static String password = "";
    private static String database = "inventory";

    private static final String CONFIG_FILE = "dbconfig.properties";

    // Cargar configuración desde el archivo
    static {
        loadConfig();
    }

    public static void configureDatabase() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el host de la base de datos [default: " + host + "]: ");
        String input = scanner.nextLine();
        if (!input.isEmpty()) host = input;

        System.out.print("Ingrese el puerto de la base de datos [default: " + port + "]: ");
        input = scanner.nextLine();
        if (!input.isEmpty()) port = input;

        System.out.print("Ingrese el usuario [default: " + user + "]: ");
        input = scanner.nextLine();
        if (!input.isEmpty()) user = input;

        System.out.print("Ingrese la contraseña (deje vacío para usar la actual): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) password = input;

        System.out.print("Ingrese el nombre de la base de datos [default: " + database + "]: ");
        input = scanner.nextLine();
        if (!input.isEmpty()) database = input;

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC";

        System.out.println("\n🔗 Probando conexión...");
        if (MySQLConnection.testConnection(url, user, password)) {
            System.out.println("✅ Conexión exitosa.");
            saveConfig(); // Guardar la configuración después de una conexión exitosa
        } else {
            System.out.println("❌ Falló la conexión. No se guardarán los cambios.");
        }
    }

    // Guardar configuración en un archivo
    private static void saveConfig() {
        try (FileOutputStream fileOut = new FileOutputStream(CONFIG_FILE)) {
            Properties props = new Properties();
            props.setProperty("host", host);
            props.setProperty("port", port);
            props.setProperty("user", user);
            props.setProperty("password", password);
            props.setProperty("database", database);
            props.store(fileOut, "Database Configuration");
            System.out.println("💾 Configuración guardada correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar la configuración: " + e.getMessage());
        }
    }

    // Cargar configuración desde el archivo si existe
    private static void loadConfig() {
        try (FileInputStream fileIn = new FileInputStream(CONFIG_FILE)) {
            Properties props = new Properties();
            props.load(fileIn);
            host = props.getProperty("host", host);
            port = props.getProperty("port", port);
            user = props.getProperty("user", user);
            password = props.getProperty("password", password);
            database = props.getProperty("database", database);
            System.out.println("📂 Configuración de base de datos cargada.");
        } catch (IOException e) {
            System.out.println("⚠ No se encontró configuración previa. Se usarán valores predeterminados.");
        }
    }

    // Métodos para obtener los valores
    public static String getHost() { return host; }
    public static String getPort() { return port; }
    public static String getUser() { return user; }
    public static String getPassword() { return password; }
    public static String getDatabase() { return database; }
}
