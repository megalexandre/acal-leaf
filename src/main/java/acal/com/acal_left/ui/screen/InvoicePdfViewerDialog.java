package acal.com.acal_left.ui.screen;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InvoicePdfViewerDialog extends JDialog {

    private final byte[] pdfBytes;
    private File tempFile;

    public InvoicePdfViewerDialog(Frame parent, byte[] pdfBytes, String title) {
        super(parent, title, true);
        this.pdfBytes = pdfBytes;
        initComponents();
        setSize(800, 600);
        setLocationRelativeTo(parent);

        SwingUtilities.invokeLater(this::openPdfAutomatically);
    }

    private void openPdfAutomatically() {
        try {
            if (tempFile == null) {
                tempFile = File.createTempFile("relatorio_", ".pdf");
                tempFile.deleteOnExit();

                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(pdfBytes);
                }
            }

            // Open with default PDF viewer
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    desktop.open(tempFile);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        // Dialog agora apenas abre o PDF automaticamente
    }

    @Override
    public void dispose() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
        super.dispose();
    }
}

