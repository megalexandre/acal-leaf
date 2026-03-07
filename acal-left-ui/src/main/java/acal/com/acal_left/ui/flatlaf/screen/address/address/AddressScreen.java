/*
 * Created by JFormDesigner on Tue Mar 03 16:38:46 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.address.address;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.usecase.address.AddressFindAllUseCase;
import acal.com.acal_left.core.usecase.address.AddressSaveUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.address.create.AddressCreate;
import acal.com.acal_left.ui.flatlaf.screen.address.model.AddressTableContent;
import acal.com.acal_left.ui.flatlaf.screen.address.model.AddressTableModel;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
@Scope("prototype")
public class AddressScreen extends JPanel {
    public final String name = Screen.ADDRESS.name();

    private final AddressFindAllUseCase findAll;
    private final AddressSaveUseCase save;

    public AddressScreen(AddressFindAllUseCase findAll, AddressSaveUseCase save) {
        this.findAll = findAll;
        this.save = save;
        initComponents();
    }

    private void searchActionListener(ActionEvent e) {
        search();
    }

    private void search(){
        table.setModel(new AddressTableModel());
        table.setAutoCreateRowSorter(true);
        AddressTableModel model = (AddressTableModel) table.getModel();

        var itens = findAll.execute().stream().map(AddressTableContent::new).toList();
        model.setList(itens);
    }

    private void createActionListener(ActionEvent e) {
        createDialog(null);
    }

    private void createDialog(Address address) {
        Window window = SwingUtilities.getWindowAncestor(this);

        AddressCreate addressCreate = new AddressCreate(window, address);
        addressCreate.pack();
        addressCreate.setLocationRelativeTo(window);
        addressCreate.setOnSuccess(e -> {
            if (e != null && e.getSource() instanceof Address toBe) {
                save.execute(toBe);
                search();
            }
        });
        addressCreate.setVisible(true);
    }

    private void tableSearchMouseClicked(MouseEvent e) {
        if (isDoubleClick(e)) {

            JTable table = (JTable) e.getSource();
            int viewRow = table.getSelectedRow();

            if (viewRow != -1) {
                int modelRow = table.convertRowIndexToModel(viewRow);
                AddressTableModel model = (AddressTableModel) table.getModel();
                Address address = model.get(modelRow);
                createDialog(address);
            }
        }
    }

    private boolean isDoubleClick(MouseEvent e) {
        return e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel2 = new JPanel();
        panel4 = new JPanel();
        buttonCreate = new JButton();
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

                //---- buttonCreate ----
                buttonCreate.setText("Adicionar");
                buttonCreate.addActionListener(e -> createActionListener(e));
                panel4.add(buttonCreate);
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
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableSearchMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel2;
    private JPanel panel4;
    private JButton buttonCreate;
    private JPanel panel5;
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
