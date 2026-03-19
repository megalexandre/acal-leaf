package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class InvoiceTable extends JTable {

    public InvoiceTable(InvoiceTableModel model) {
        super(model);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getRowCount() == 0) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2.setColor(UIManager.getColor("Label.disabledForeground"));
            g2.setFont(getFont().deriveFont(Font.ITALIC, 14f));

            String message = "Nenhum registro carregado. Utilize os filtros para pesquisar.";

            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(message)) / 2;
            int y = getHeight() / 2;

            g2.drawString(message, x, y);
            g2.dispose();
        }
    }
}