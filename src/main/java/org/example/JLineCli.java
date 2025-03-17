package org.example;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class JLineCli {
    public static void start() throws Exception {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new StringsCompleter("help", "inventory", "sell", "menu", "connect", "exit"))
                .build();

        System.out.println("\u001B[32m🚀 System Of Sales at Terminal\u001B[0m");

        while (true) {
            String input = reader.readLine("\u001B[34m🔹 Inventor-> \u001B[0m").trim();

            switch (input.toLowerCase()) {
                case "exit":
                    System.out.println("\u001B[31m🔴 Saliendo del sistema...\u001B[0m");
                    return;
                case "help":
                    System.out.println("\u001B[36m📜 Comandos disponibles: help, inventory, sell <producto>, menu, connect, exit\u001B[0m");
                    break;
                case "connect":
                    configureDatabase(reader);
                    break;
                case "inventory":
                    DatabaseConnector.fetchInventory();
                    break;
                case "menu":
                    showLanternaMenu();
                    break;
                default:
                    if (input.startsWith("sell")) {
                        String product = input.substring(4).trim();
                        if (product.isEmpty()) {
                            System.out.println("\u001B[33m⚠️ Especifica un producto a vender.\u001B[0m");
                        } else {
                            System.out.println("\u001B[32m✅ Vendiendo producto: " + product + "\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31m❌ Comando no reconocido: " + input + "\u001B[0m");
                    }
                    break;
            }
        }
    }

    public static void showLanternaMenu() {
        try {
            Screen screen = new DefaultTerminalFactory().createScreen();
            screen.startScreen();
            ModernLanternaMenu.display(screen);
        } catch (IOException e) {
            System.err.println("\u001B[31m❌ Error al iniciar el menú: " + e.getMessage() + "\u001B[0m");
        }
    }
}
