package acal.com.acal_left.ui.screen.boostrap;

import javax.swing.*;
import java.awt.*;

public class SplashScreenBootstrap {

    private static JFrame frame;

    public static void showSplash() {
        try {
            SwingUtilities.invokeAndWait(()->{
                frame = new JFrame("Acal Left - Carregando...");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
                frame.add(getJPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        } catch (Exception ignored) {
        }
    }


    public static void closeSplash() {
        SwingUtilities.invokeLater(()->{
            if (frame != null) {
                frame.setVisible(false);
                frame.dispose();
                frame = null;
            }
        });
    }

    private static JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 150, 243));
        panel.setPreferredSize(new Dimension(500, 300));
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Acal Left");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel loadingLabel = new JLabel("Afofando as nuvens...");
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(loadingLabel, BorderLayout.SOUTH);
        return panel;
    }
}
