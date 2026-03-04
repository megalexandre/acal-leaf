package acal.com.acal_left.ui.boostrap;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public final class SplashScreenBootstrap {

    private static Timer textTimer;
    private static int phraseIndex = 0;
    private static final String[] PHRASES = {
            "Carregando módulos...",
            "Sincronizando dados...",
            "Preparando interface...",
            "Quase pronto..."
    };


    private static final Object LOCK = new Object();
    private static JWindow splashWindow;

    private SplashScreenBootstrap() {
    }

    public static void showSplash() {
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        if (splashWindow != null && splashWindow.isVisible()) {
            return;
        }

        Runnable task = () -> {
            synchronized (LOCK) {
                if (splashWindow == null) {
                    splashWindow = buildWindow();
                    splashWindow.setVisible(true);
                }
            }
        };

        runOnEdtAndWait(task);
}

    public static void closeSplash() {
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            if (textTimer != null) {
                textTimer.stop();
                textTimer = null;
            }

            synchronized (LOCK) {
                if (splashWindow != null) {
                    splashWindow.setVisible(false);
                    splashWindow.dispose();
                    splashWindow = null;
                }
            }
        });
    }

    private static JWindow buildWindow() {
        JWindow window = new JWindow();
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(245, 247, 250));
        content.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(20, 26, 18, 26)
        ));

        JLabel title = new JLabel("Acal Left", SwingConstants.CENTER);
        title.setAlignmentX(0.5f);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        title.setForeground(new Color(30, 35, 44));

        JLabel subtitle = new JLabel(PHRASES[0], SwingConstants.CENTER);
        subtitle.setAlignmentX(0.5f);
        subtitle.setFont(subtitle.getFont().deriveFont(Font.PLAIN, 13f));
        subtitle.setForeground(new Color(85, 92, 103));

        textTimer = new Timer(2000, e -> {
            phraseIndex = (phraseIndex + 1) % PHRASES.length;
            subtitle.setText(PHRASES[phraseIndex]);
        });
        textTimer.start();

        JProgressBar progress = new JProgressBar();
        progress.setIndeterminate(true);
        progress.setBorderPainted(false);
        progress.setForeground(new Color(70, 120, 200));
        progress.setBackground(new Color(226, 232, 240));
        progress.setMaximumSize(new Dimension(320, 6));
        progress.setPreferredSize(new Dimension(320, 6));
        progress.setMinimumSize(new Dimension(320, 6));
        progress.setAlignmentX(0.5f);

        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 8)));
        content.add(subtitle);
        content.add(Box.createRigidArea(new Dimension(0, 14)));
        content.add(progress);

        window.setContentPane(content);
        window.setSize(380, 150);
        window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);
        window.setFocusableWindowState(false);
        window.setType(java.awt.Window.Type.UTILITY);

        return window;
    }

    private static void runOnEdtAndWait(Runnable task) {
        if (SwingUtilities.isEventDispatchThread()) {
            task.run();
            return;
        }

        try {
            SwingUtilities.invokeAndWait(task);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException("Nao foi possivel exibir splash screen.", ex.getCause());
        }
    }
}
