package org.example;
import java.util.logging.*;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            JLineCli.start();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al iniciar JLineCLI", e);
        }
    }
}
