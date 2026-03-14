/*
 * Created by JFormDesigner on Fri Mar 13 11:46:40 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.charge;

import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.usecase.address.AddressFindAllUseCase;
import acal.com.acal_left.core.usecase.charge.ChargeListUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxLoader;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxOption;
import acal.com.acal_left.ui.flatlaf.screen.charge.model.ChargeTableContent;
import acal.com.acal_left.ui.flatlaf.screen.charge.model.ChargeTableModel;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Scope("prototype")
public class ChargeScreen extends JPanel {
    public final String name = Screen.CHARGE.name();

    @Autowired
    private ChargeListUseCase list;

    @Autowired
    private AddressFindAllUseCase addressFind;

    public ChargeScreen() {
        initComponents();

        buttonSearch.addActionListener(e -> {
            fetchPageData();
        });

        ComboBoxLoader.setupLazyLoad(comboBoxAddress, this::getOrLoadAddresses);
    }

    private List<ComboBoxOption> getOrLoadAddresses() {
        return addressFind.execute().stream()
                .map(it -> new ComboBoxOption(it.getId(), it.getFullAddress()))
                .toList();
    }

    private void fetchPageData() {
        var query = InvoiceQuery.builder()
                .paid(false)
                .dueDateStart(LocalDateTime.MIN)
                .dueDateEnd(LocalDateTime.now().minusDays(59))
                .addressId(getAddressId())
                .build();

        var charges = list.execute(query).stream().map(ChargeTableContent::new).toList();
        var model = new ChargeTableModel();
        if(charges.isEmpty()) {
           return;
        }
        model.setList(charges);
        table.setAutoCreateRowSorter(true);
        table.setModel(model);
    }

    private Integer getAddressId(){
        return ComboBoxOption.getSelectedId(comboBoxAddress);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel4 = new JPanel();
        label1 = new JLabel();
        comboBoxAddress = new JComboBox<>();
        panel3 = new JPanel();
        buttonSearch = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new VerticalLayout());

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout());

                //======== panel4 ========
                {
                    panel4.setLayout(new HorizontalLayout());

                    //---- label1 ----
                    label1.setText("Endere\u00e7o:");
                    panel4.add(label1);
                    panel4.add(comboBoxAddress);
                }
                panel2.add(panel4);
            }
            panel1.add(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- buttonSearch ----
                buttonSearch.setText("Buscar");
                panel3.add(buttonSearch);
            }
            panel1.add(panel3);
        }
        add(panel1, BorderLayout.SOUTH);

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel4;
    private JLabel label1;
    private JComboBox<ComboBoxOption> comboBoxAddress;
    private JPanel panel3;
    private JButton buttonSearch;
    private JScrollPane scrollPane1;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
