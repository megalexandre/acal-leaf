package acal.com.acal_left.ui.flatlaf.utils;

import javax.swing.UIManager;
import java.awt.Color;

public class UIManagerColor {

    // ============ CORES DE TABELA ============
    public static final Color TABLE_BACKGROUND = UIManager.getColor("Table.background");
    public static final Color TABLE_FOREGROUND = UIManager.getColor("Table.foreground");
    public static final Color TABLE_SELECTION_BACKGROUND = UIManager.getColor("Table.selectionBackground");
    public static final Color TABLE_SELECTION_FOREGROUND = UIManager.getColor("Table.selectionForeground");
    public static final Color TABLE_GRID_COLOR = UIManager.getColor("Table.gridColor");
    public static final Color TABLE_FOCUS_CELL_BACKGROUND = UIManager.getColor("Table.focusCellBackground");
    public static final Color TABLE_FOCUS_CELL_FOREGROUND = UIManager.getColor("Table.focusCellForeground");

    // ============ CORES DE CABEÇALHO DE TABELA ============
    public static final Color TABLE_HEADER_BACKGROUND = UIManager.getColor("TableHeader.background");
    public static final Color TABLE_HEADER_FOREGROUND = UIManager.getColor("TableHeader.foreground");
    public static final Color TABLE_HEADER_FOCUS_CELL_BACKGROUND = UIManager.getColor("TableHeader.focusCellBackground");

    // ============ CORES DE BOTÃO ============
    public static final Color BUTTON_BACKGROUND = UIManager.getColor("Button.background");
    public static final Color BUTTON_FOREGROUND = UIManager.getColor("Button.foreground");
    public static final Color BUTTON_FOCUS = UIManager.getColor("Button.focus");
    public static final Color BUTTON_SELECT = UIManager.getColor("Button.select");

    // ============ CORES DE PAINEL ============
    public static final Color PANEL_BACKGROUND = UIManager.getColor("Panel.background");
    public static final Color PANEL_FOREGROUND = UIManager.getColor("Panel.foreground");

    // ============ CORES DE TEXTO ============
    public static final Color TEXT_BACKGROUND = UIManager.getColor("TextField.background");
    public static final Color TEXT_FOREGROUND = UIManager.getColor("TextField.foreground");
    public static final Color TEXT_SELECTION_BACKGROUND = UIManager.getColor("TextField.selectionBackground");
    public static final Color TEXT_SELECTION_FOREGROUND = UIManager.getColor("TextField.selectionForeground");

    // ============ CORES DE MENU ============
    public static final Color MENU_BACKGROUND = UIManager.getColor("Menu.background");
    public static final Color MENU_FOREGROUND = UIManager.getColor("Menu.foreground");
    public static final Color MENU_SELECTION_BACKGROUND = UIManager.getColor("Menu.selectionBackground");
    public static final Color MENU_SELECTION_FOREGROUND = UIManager.getColor("Menu.selectionForeground");

    // ============ CORES DE BARRA DE SCROLL ============
    public static final Color SCROLLBAR_BACKGROUND = UIManager.getColor("ScrollBar.background");
    public static final Color SCROLLBAR_THUMB = UIManager.getColor("ScrollBar.thumb");
    public static final Color SCROLLBAR_TRACK = UIManager.getColor("ScrollBar.track");

    // ============ CORES DE LABEL ============
    public static final Color LABEL_BACKGROUND = UIManager.getColor("Label.background");
    public static final Color LABEL_FOREGROUND = UIManager.getColor("Label.foreground");
    public static final Color LABEL_DISABLED_FOREGROUND = UIManager.getColor("Label.disabledForeground");

    // ============ CORES DE CONTROLE GERAL ============
    public static final Color CONTROL = UIManager.getColor("control");
    public static final Color CONTROL_TEXT = UIManager.getColor("controlText");
    public static final Color CONTROL_HIGHLIGHT = UIManager.getColor("controlHighlight");
    public static final Color CONTROL_SHADOW = UIManager.getColor("controlShadow");

    // ============ CORES UTILITÁRIAS ============
    public static final Color WINDOW_BACKGROUND = UIManager.getColor("window");
    public static final Color DESKTOP_BACKGROUND = UIManager.getColor("desktop");

    // ============ CORES BOOTSTRAP ============
    public static final Color SUCCESS = new Color(40, 167, 69);           // #28a745
    public static final Color SUCCESS_LIGHT = new Color(198, 239, 206);  // #c6efce

    public static final Color DANGER = new Color(220, 53, 69);           // #dc3545
    public static final Color DANGER_LIGHT = new Color(248, 215, 218);   // #f8d7da

    public static final Color WARNING = new Color(255, 193, 7);          // #ffc107
    public static final Color WARNING_LIGHT = new Color(255, 243, 205);  // #fff3cd

    public static final Color INFO = new Color(17, 182, 214);            // #11b6d6
    public static final Color INFO_LIGHT = new Color(204, 240, 248);     // #ccf0f8

    public static final Color PRIMARY = new Color(0, 123, 255);          // #007bff
    public static final Color PRIMARY_LIGHT = new Color(207, 230, 254);  // #cfe6fe

    public static final Color SECONDARY = new Color(108, 117, 125);      // #6c757d
    public static final Color SECONDARY_LIGHT = new Color(230, 231, 232);// #e6e7e8

    public static final Color LIGHT = new Color(247, 248, 250);          // #f7f8fa
    public static final Color LIGHT_LIGHTER = new Color(253, 253, 253);  // #fdfdfd

    public static final Color DARK = new Color(52, 58, 64);              // #343a40
    public static final Color DARK_LIGHT = new Color(141, 145, 150);     // #8d9196

    /**
     * Retorna uma cor pelo nome da chave do UIManager
     */
    public static Color getColor(String key) {
        Color color = UIManager.getColor(key);
        if (color == null) {
            System.err.println("Aviso: Cor '" + key + "' não encontrada no tema!");
        }
        return color;
    }

    /**
     * Retorna uma cor em formato hexadecimal
     */
    public static String getColorHex(Color color) {
        if (color == null) {
            return "#000000";
        }
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Retorna uma cor em formato RGB
     */
    public static String getColorRGB(Color color) {
        if (color == null) {
            return "RGB(0, 0, 0)";
        }
        return String.format("RGB(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
    }


}

