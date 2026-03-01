package acal.com.acal_left.ui.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

        // Automatically open PDF when dialog is shown
        SwingUtilities.invokeLater(this::openPdfAutomatically);
    }

    /**
     * Automatically opens the PDF with the default system viewer
     */
    private void openPdfAutomatically() {
        try {
            // Create temporary file
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
            // Silently fail - user can still use the "Open" button manually
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel messageLabel = new JLabel(
            "<html><div style='text-align: center;'>" +
            "<h2>✅ Relatório gerado com sucesso!</h2>" +
            "<p>Tamanho: " + (pdfBytes.length / 1024) + " KB</p>" +
            "<p>Clique em 'Salvar PDF' para escolher onde salvar o arquivo.</p>" +
            "</div></html>"
        );
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton saveButton = new JButton("Salvar PDF");
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.addActionListener(this::savePdfAction);

        JButton openButton = new JButton("Abrir com Leitor PDF");
        openButton.setPreferredSize(new Dimension(180, 40));
        openButton.addActionListener(this::openPdfAction);

        JButton closeButton = new JButton("Fechar");
        closeButton.setPreferredSize(new Dimension(150, 40));
        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void savePdfAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Relatório PDF");
        fileChooser.setSelectedFile(new File("relatorio_contas.pdf"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Ensure .pdf extension
            if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                fos.write(pdfBytes);
                JOptionPane.showMessageDialog(
                    this,
                    "PDF salvo com sucesso em:\n" + fileToSave.getAbsolutePath(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar PDF: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void openPdfAction(ActionEvent e) {
        openPdfAutomatically();
    }

    @Override
    public void dispose() {
        // Clean up temp file
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
        super.dispose();
    }
}

