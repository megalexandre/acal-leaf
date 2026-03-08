package acal.com.acal_left.ui.flatlaf.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Toast {
    public static void show(Component parent, String message) {
        Window window = SwingUtilities.getWindowAncestor(parent);
        JWindow toast = new JWindow(window);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setBackground(new Color(50, 50, 50, 200)); // Fundo escuro semi-transparente
        
        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        panel.add(label);
        
        toast.add(panel);
        toast.pack();

        // Posicionamento (Canto inferior direito da janela pai)
        int x = window.getX() + window.getWidth() - toast.getWidth() - 20;
        int y = window.getY() + window.getHeight() - toast.getHeight() - 50;
        toast.setLocation(x, y);
        
        // Arredondar bordas
        toast.setShape(new RoundRectangle2D.Double(0, 0, toast.getWidth(), toast.getHeight(), 15, 15));
        toast.setVisible(true);

        // Timer para fechar sozinho após 3 segundos
        new Timer(3000, e -> {
            toast.dispose();
        }).start();
    }
}