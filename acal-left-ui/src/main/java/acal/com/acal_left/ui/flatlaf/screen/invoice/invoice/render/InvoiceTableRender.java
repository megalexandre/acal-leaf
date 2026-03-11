package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.render;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class InvoiceTableRender extends DefaultTableCellRenderer {



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);



        /*
        InvoiceTableModel model = (InvoiceTableModel) table.getModel();
        int modelRow = table.convertRowIndexToModel(row);

        Invoice invoice = model.get(modelRow);

        boolean isPair = modelRow % 2 == 0;

        var success = UIManagerColorPrinter.SUCCESS;
        if(isPair){
            success = UIManagerColorPrinter.SUCCESS_LIGHT;
        }

        if ( invoice.isPaid()) {
            c.setBackground(success);
        }
         */

        return c;
    }
}


