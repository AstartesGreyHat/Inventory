package org.example;


import Functions.seeInventary;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JLineCli {
    public static void start() throws Exception {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setTerminalEmulatorColorConfiguration(
                TerminalEmulatorColorConfiguration.newInstance(TerminalEmulatorPalette.XTERM)
        );

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new StringsCompleter("help", "inventory", "sell", "menu", "config-db", "exit"))
                .build();


        while (true) {
            String input = reader.readLine("Inventory-> ").trim();
            switch (input.toLowerCase()) {
                case "exit":
                    System.out.println("Exit...");
                    return;
                case "help":
                    System.out.println("Comandos: help, inventory, sell <producto>, menu, config-db, exit");
                    break;
                case "inventory":
                    System.out.println("📦 Mostrando inventario...");
                    break;
                case "menu":
                    showLanternaMenu();
                    break;
                case "config-db":
                    DatabaseConfig.configureDatabase();
                    break;
                default:
                    if (input.startsWith("sell ")) {
                        String product = input.substring(5);
                        System.out.println("🛒 Vendiendo: " + product);
                    } else {
                        System.out.println("Comando no reconocido.");
                    }
            }
        }
    }

    public static void showLanternaMenu() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();

        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window window = new BasicWindow("Inventor 1.0");
        window.setHints(List.of(Window.Hint.CENTERED));

        // ✅ Aplicar el tema personalizado
        Theme customTheme = CustomTheme.getDarkTheme();

        Panel panel = new Panel();
        panel.setTheme(customTheme);


        panel.addComponent(new Label("[*]Seleccione una opción:"));
        panel.addComponent(new Button("📦 Ver Inventario", () -> {
            try {
                screen.stopScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            seeInventary.showInventory();

        }
        ));
        panel.addComponent(new Button("🛒 Vender Producto", () -> System.out.println("🛒 Venta procesada")));
        panel.addComponent(new Button("❌ Salir", window::close));

        window.setComponent(panel);
        textGUI.addWindowAndWait(window);
        screen.stopScreen();

        // ✅ Restaurar colores ANSI después de cerrar Lanterna
        System.out.print("\033[0m\033[H\033[2J");
        System.out.flush();
    }
}
