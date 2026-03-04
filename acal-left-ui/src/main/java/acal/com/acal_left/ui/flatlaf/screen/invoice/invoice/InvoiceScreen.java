package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice;

import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.usecase.invoice.InvoiceFindUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.category.model.CategoryTableModel;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableContent;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableModel;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@Component
@Scope("prototype")
public class InvoiceScreen extends JPanel {
    public final String name = Screen.INVOICE.name();
    private final InvoiceFindUseCase find;

    public InvoiceScreen(InvoiceFindUseCase find) {
        this.find = find;
        initComponents();
    }

    private void searchActionListener(ActionEvent e) {
        table.setModel(new InvoiceTableModel());
        InvoiceTableModel model = (InvoiceTableModel) table.getModel();
        var itens = find.execute(InvoiceQuery.builder().build()).stream().map(InvoiceTableContent::new).toList();
        model.setList(itens);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        panel3 = new JPanel();
        panel2 = new JPanel();
        buttonSearch = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new VerticalLayout());

            //======== panel3 ========
            {
                panel3.setBorder(new CompoundBorder(
                    new TitledBorder("Filtros"),
                    new EmptyBorder(5, 5, 5, 5)));
                panel3.setLayout(new VerticalLayout());
            }
            panel1.add(panel3);

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- buttonSearch ----
                buttonSearch.setText("Consultas");
                buttonSearch.addActionListener(e -> searchActionListener(e));
                panel2.add(buttonSearch);
            }
            panel1.add(panel2);
        }
        add(panel1, BorderLayout.SOUTH);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel1;
    private JPanel panel3;
    private JPanel panel2;
    private JButton buttonSearch;
    private JScrollPane scrollPane1;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
