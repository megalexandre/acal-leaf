package acal.com.acal_left.ui.boostrap;

import org.jspecify.annotations.NonNull;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenBootstrap {
    private static JFrame frame;
    private static JLabel loadingLabel;
    private static Timer messageTimer;
    private static int currentMessageIndex = 0;

    private static final String[] LOADING_MESSAGES = {
        "Afofando as nuvens...",
        "Preparando o pix..."
    };

    static {
        System.setProperty("sun.awt.disablegrab", "true");
        System.setProperty("java.awt.headless", "false");
    }

    public static void showSplash() {
        try {
            if (GraphicsEnvironment.isHeadless()) {
                return;
            }

            frame = new JFrame("Acal Left - Carregando...");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setUndecorated(true);
            frame.setAlwaysOnTop(true);

            JPanel panel = getJPanel();

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Inicia o timer para trocar mensagens a cada 5 segundos
            startMessageRotation();

        } catch (Exception e) {
            System.err.println("Erro ao exibir splash screen: " + e.getMessage());
        }
    }

    private static void startMessageRotation() {
        messageTimer = new Timer("SplashMessageTimer", true);
        messageTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (loadingLabel != null && frame != null && frame.isVisible()) {
                    SwingUtilities.invokeLater(() -> {
                        loadingLabel.setText(LOADING_MESSAGES[currentMessageIndex]);
                        currentMessageIndex = (currentMessageIndex + 1) % LOADING_MESSAGES.length;
                    });
                }
            }
        }, 5000, 5000); // Primeira execução em 5 segundos, depois a cada 5 segundos
    }

    private static @NonNull JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 150, 243));
        panel.setPreferredSize(new Dimension(500, 300));
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Acal Left");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Reseta o índice para começar do início
        currentMessageIndex = 0;
        loadingLabel = new JLabel(LOADING_MESSAGES[currentMessageIndex]);
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(loadingLabel, BorderLayout.SOUTH);
        return panel;
    }


    public static void closeSplash() {
        // Para o timer de mensagens
        if (messageTimer != null) {
            messageTimer.cancel();
            messageTimer = null;
        }

        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
            frame = null;
            System.out.println("Splash screen fechada!");
        }
    }
}
