package acal.com.acal_left.ui.flatlaf.screen.invoice.create;

import acal.com.acal_left.core.model.Hydrometer;
import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceGenerateFilter;
import acal.com.acal_left.core.usecase.invoice.InvoiceCreateUseCase;
import acal.com.acal_left.core.usecase.invoice.InvoiceGenerateUseCase;
import acal.com.acal_left.shared.model.GenerateInvoiceType;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.component.filter.LocalDateField;
import acal.com.acal_left.ui.flatlaf.component.filter.MonthYearField;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxOption;
import acal.com.acal_left.ui.flatlaf.screen.invoice.create.model.InvoiceGenerateTableContent;
import acal.com.acal_left.ui.flatlaf.screen.invoice.create.model.InvoiceGenerateTableModel;
import lombok.val;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import static acal.com.acal_left.shared.model.GenerateInvoiceType.ALL;
import static acal.com.acal_left.shared.model.GenerateInvoiceType.WITHOUT_HYDROMETER;
import static acal.com.acal_left.shared.model.GenerateInvoiceType.WITH_HYDROMETER;

@Component
@Scope("prototype")
public class InvoiceCreateScreen extends JPanel {
    public final String name = Screen.INVOICE_CREATE.name();

    // Campo fora do bloco gerado — o JFormDesigner nunca sobrescreve
    private final MonthYearField monthYearField = new MonthYearField();
    private final LocalDateField dueDateField = new LocalDateField();

    @Autowired
    private InvoiceGenerateUseCase generate;

    @Autowired
    private InvoiceCreateUseCase create;


    public InvoiceCreateScreen() {
        initComponents();

        init();
    }

    private void init() {
        comboBoxType.setModel(new DefaultComboBoxModel<>(new ComboBoxOption[] {
            new ComboBoxOption(null, ""),
            new ComboBoxOption(ALL.getValue(),                ALL.getDescription()),
            new ComboBoxOption(WITH_HYDROMETER.getValue(),    WITH_HYDROMETER.getDescription()),
            new ComboBoxOption(WITHOUT_HYDROMETER.getValue(), WITHOUT_HYDROMETER.getDescription()),
        }));

        // Substitui o JFormattedTextField gerado pelo nosso MonthYearField customizado
        panel6.remove(formattedPeriod);
        monthYearField.setPreferredSize(new Dimension(150, 25));
        panel6.add(monthYearField);
        panel6.revalidate();
        panel6.repaint();

        dueDateField.setPreferredSize(new Dimension(150, 25));
        panelDueDate.add(dueDateField);
        panelDueDate.revalidate();
        panelDueDate.repaint();

        buttonSearch.setEnabled(false);
        comboBoxType.addActionListener(e -> updateSearchButton());
        monthYearField.addChangeListener(this::updateSearchButton);
        buttonSearch.addActionListener(e -> search());
        buttonConfirm.addActionListener(e -> confirm());
    }

    private void confirm(){
        InvoiceGenerateTableModel model = (InvoiceGenerateTableModel) table.getModel();
        if (model == null) return;

        List<Invoice> itemsToGenerate = model.getItems().stream()
            .filter(InvoiceGenerateTableContent::isGenerate)
            .map(
                it -> {
                    val invoice = it.getItem();
                    invoice.setHydrometer(Hydrometer
                        .builder()
                            .consumptionStart(Double.valueOf(it.getHydrometerStart()))
                            .consumptionEnd(Double.valueOf(it.getHydrometerEnd()))
                        .build());
                    invoice.setDueDate(dueDateField.getLocalDate().atStartOfDay());
                    return invoice;
                }
            )
            .toList();

        create.execute(itemsToGenerate);
        search();
    }

    private void updateSearchButton() {
        Integer id = ComboBoxOption.getSelectedId(comboBoxType);
        buttonSearch.setEnabled(id != null && monthYearField.isComplete());
    }


    private void toggleAllActionListener(ActionEvent e) {
        InvoiceGenerateTableModel model = (InvoiceGenerateTableModel) table.getModel();
        if (model == null) return;

        boolean allSelected = model.getItems().stream().allMatch(InvoiceGenerateTableContent::isGenerate);
        model.getItems().forEach(item -> item.setGenerate(!allSelected));
        model.fireTableDataChanged();
    }

    private void search(){
        Integer id = ComboBoxOption.getSelectedId(comboBoxType);
        if (id == null) return;

        GenerateInvoiceType type = GenerateInvoiceType.from(id);

        InvoiceGenerateFilter filter = InvoiceGenerateFilter.builder()
                .type(type)
                .reference(monthYearField.getYearMonth())
                .build();

        val invoices = generate.execute(filter);
        val invoiceItems = invoices.stream()
                .map(InvoiceGenerateTableContent::new)
                .toList();

        InvoiceGenerateTableModel model = new InvoiceGenerateTableModel();
        model.setList(invoiceItems);
        table.setModel(model);

        int startCol = InvoiceGenerateTableModel.InvoiceColumns.HYDROMETER_STARTS.ordinal();
        int endCol   = InvoiceGenerateTableModel.InvoiceColumns.HYDROMETER_ENDS.ordinal();
        table.getColumnModel().getColumn(startCol).setCellEditor(InvoiceGenerateTableModel.numericCellEditor());
        table.getColumnModel().getColumn(endCol).setCellEditor(InvoiceGenerateTableModel.numericCellEditor());
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel1 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        label1 = new JLabel();
        comboBoxType = new JComboBox<>();
        panel6 = new JPanel();
        label2 = new JLabel();
        formattedPeriod = new JFormattedTextField();
        panel7 = new JPanel();
        label3 = new JLabel();
        buttonSearch = new JButton();
        panel5 = new JPanel();
        panelDueDate = new JPanel();
        panel8 = new JPanel();
        buttonToggleAll2 = new JButton();
        buttonConfirm = new JButton();

        //======== this ========
        setLayout(new BorderLayout(10, 10));

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowVerticalLines(true);
            table.setShowHorizontalLines(true);
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel1.setLayout(new BorderLayout(10, 10));

            //======== panel3 ========
            {
                panel3.setLayout(new HorizontalLayout(10));

                //======== panel4 ========
                {
                    panel4.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("Tipo Hidr\u00f4metro:");
                    panel4.add(label1);

                    //---- comboBoxType ----
                    comboBoxType.setPreferredSize(new Dimension(150, 25));
                    panel4.add(comboBoxType);
                }
                panel3.add(panel4);

                //======== panel6 ========
                {
                    panel6.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("Ref\u00earencia:");
                    panel6.add(label2);

                    //---- formattedPeriod ----
                    formattedPeriod.setPreferredSize(new Dimension(150, 25));
                    panel6.add(formattedPeriod);
                }
                panel3.add(panel6);

                //======== panel7 ========
                {
                    panel7.setLayout(new VerticalLayout());

                    //---- label3 ----
                    label3.setText("Buscar");
                    panel7.add(label3);

                    //---- buttonSearch ----
                    buttonSearch.setText("Buscar");
                    panel7.add(buttonSearch);
                }
                panel3.add(panel7);
            }
            panel1.add(panel3, BorderLayout.CENTER);

            //======== panel5 ========
            {
                panel5.setLayout(new HorizontalLayout(10));

                //======== panelDueDate ========
                {
                    panelDueDate.setLayout(new VerticalLayout(10));
                }
                panel5.add(panelDueDate);

                //======== panel8 ========
                {
                    panel8.setLayout(new VerticalLayout(10));

                    //---- buttonToggleAll2 ----
                    buttonToggleAll2.setText("Marcar/Desmarcar");
                    buttonToggleAll2.setPreferredSize(new Dimension(150, 25));
                    buttonToggleAll2.addActionListener(e -> toggleAllActionListener(e));
                    panel8.add(buttonToggleAll2);

                    //---- buttonConfirm ----
                    buttonConfirm.setText("Confirmar");
                    buttonConfirm.setPreferredSize(new Dimension(150, 25));
                    panel8.add(buttonConfirm);
                }
                panel5.add(panel8);
            }
            panel1.add(panel5, BorderLayout.EAST);
        }
        add(panel1, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel1;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label1;
    private JComboBox<ComboBoxOption> comboBoxType;
    private JPanel panel6;
    private JLabel label2;
    private JFormattedTextField formattedPeriod;
    private JPanel panel7;
    private JLabel label3;
    private JButton buttonSearch;
    private JPanel panel5;
    private JPanel panelDueDate;
    private JPanel panel8;
    private JButton buttonToggleAll2;
    private JButton buttonConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
