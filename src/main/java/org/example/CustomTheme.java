package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.Theme;

public class CustomTheme {
    public static Theme getDarkTheme() {
        return SimpleTheme.makeTheme(
                true,
                TextColor.ANSI.WHITE,       // Texto normal en blanco
                TextColor.ANSI.BLACK,       // Fondo negro general
                TextColor.ANSI.YELLOW,      // Texto en campos editables
                TextColor.ANSI.BLACK,       // Fondo de campos editables en negro
                TextColor.ANSI.WHITE,// Texto de elementos seleccionados en verde brillante
                TextColor.ANSI.BLACK_BRIGHT, // Fondo azul brillante cuando está seleccionado
                TextColor.ANSI.BLACK        // Fondo general de la interfaz
        );
    }
}
