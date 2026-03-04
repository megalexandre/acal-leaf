/*
 * Created by JFormDesigner on Tue Mar 03 16:38:46 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.address.address;

import acal.com.acal_left.core.usecase.address.AddressFindAllUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.address.model.AddressTableContent;
import acal.com.acal_left.ui.flatlaf.screen.address.model.AddressTableModel;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Component
@Scope("prototype")
public class AddressScreen extends JPanel {
    public final String name = Screen.ADDRESS.name();

    private final AddressFindAllUseCase findAll;

    public AddressScreen(AddressFindAllUseCase findAll) {
        this.findAll = findAll;
        initComponents();
    }

    private void searchActionListener(ActionEvent e) {
        table.setModel(new AddressTableModel());
        AddressTableModel model = (AddressTableModel) table.getModel();

        var itens = findAll.execute().stream().map(AddressTableContent::new).toList();
        model.setList(itens);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel2 = new JPanel();
        panel4 = new JPanel();
        button1 = new JButton();
        panel5 = new JPanel();
        button2 = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel2 ========
        {
            panel2.setLayout(new VerticalLayout());

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- button1 ----
                button1.setText("Adicionar");
                panel4.add(button1);
            }
            panel2.add(panel4);
        }
        add(panel2, BorderLayout.NORTH);

        //======== panel5 ========
        {
            panel5.setLayout(new FlowLayout(FlowLayout.RIGHT));

            //---- button2 ----
            button2.setText("Consultar");
            button2.addActionListener(e -> searchActionListener(e));
            panel5.add(button2);
        }
        add(panel5, BorderLayout.SOUTH);

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel2;
    private JPanel panel4;
    private JButton button1;
    private JPanel panel5;
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
