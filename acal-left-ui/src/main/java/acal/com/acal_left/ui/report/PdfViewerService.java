package acal.com.acal_left.ui.report;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfViewerService {

    public void openPdf(byte[] pdfBytes) {
        try {

            File tempFile = File.createTempFile("relatorio_contas_", ".pdf");
            tempFile.deleteOnExit();

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(pdfBytes);
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(tempFile);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                null,
                "Erro ao abrir PDF: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

