/*
 * Created by JFormDesigner on Wed Mar 11 13:01:48 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.register;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.usecase.invoice.InvoiceListUseCase;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import acal.com.acal_left.shared.LocalDateUtil;
import acal.com.acal_left.shared.model.PaymentType;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxOption;
import acal.com.acal_left.ui.flatlaf.screen.register.model.RegisterTableContent;
import acal.com.acal_left.ui.flatlaf.screen.register.model.RegisterTableModel;
import acal.com.acal_left.ui.report.PdfViewerService;
import acal.com.acal_left.ui.report.RegisterReportService;
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
import javax.swing.text.MaskFormatter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@Scope("prototype")
public class RegisterScreen extends JPanel {
    public final String name = Screen.REGISTER.name();

    @Autowired
    private InvoiceListUseCase list;

    @Autowired
    private PdfViewerService pdfViewerService;

    private final RegisterReportService reportService = new RegisterReportService();

    private List<Invoice> lastInvoices = List.of();

    public RegisterScreen() {
        initComponents();
        init();
    }

    private void init(){
        createMask(formattedTextFieldStart, "##/##/####");
        createMask(formattedTextFieldEnd, "##/##/####");

        table.setAutoCreateRowSorter(true);
        table.setModel(new RegisterTableModel());

        comboBoxPaymentType.setModel(new DefaultComboBoxModel<>(new ComboBoxOption[] {
            new ComboBoxOption(null, ""),
            new ComboBoxOption(PaymentType.MONEY.getValue(),PaymentType.MONEY.getDescription()),
            new ComboBoxOption(PaymentType.PIX.getValue(),PaymentType.PIX.getDescription()),
        }));
    }

    private void buttonActionListener(ActionEvent e) {
        initDateFields();
        List<Invoice> invoices = fetchData();

        RegisterTableModel model = (RegisterTableModel) table.getModel();
        var itens = invoices.stream().map(RegisterTableContent::new).toList();
        model.setList(itens);
        table.setModel(model);

        updateLabel(invoices);
    }

    private List<Invoice> fetchData(){
        var filter = InvoiceQuery.builder()
                .paid(true)
                .periodStart(getStart())
                .periodEnd(getEnd())
                .paymentType(getPaymentType())
                .build();

        var invoices = list.execute(filter);
        this.lastInvoices = invoices;
        return invoices;
    }

    private void updateLabel(List<Invoice> invoices) {
        if (invoices.isEmpty()) {
            label.setText("Nenhuma nota fiscal encontrada para o período.");
        } else {
            var total = invoices.stream()
                    .map(Invoice::totalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            label.setText(String.format("%d registro(s) com um total de %s",
                    invoices.size(),
                    BigDecimalUtil.toBRL(total)));
        }
    }

    private void createMask(JFormattedTextField field, String mask){
        try{
            MaskFormatter item = new MaskFormatter(mask);
            item.setPlaceholderCharacter('_');
            item.install(field);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime getStart() {
        return processDateTime(formattedTextFieldStart, true);
    }

    private LocalDateTime getEnd() {
        return processDateTime(formattedTextFieldEnd, false);
    }

    private PaymentType getPaymentType() {
        Integer selectedId = ComboBoxOption.getSelectedId(comboBoxPaymentType);
        return selectedId != null ? PaymentType.from(selectedId) : null;
    }

    private LocalDateTime processDateTime(JFormattedTextField field, boolean isStart) {
        var date = LocalDateUtil.fromString(field.getText());
        return isStart ? date.atStartOfDay() : date.atTime(LocalTime.MAX);
    }

    private void printActionListener(ActionEvent e) {
        if (lastInvoices.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Nenhum dado para imprimir. Realize uma consulta primeiro.",
                    "Relatório de Caixa",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        byte[] pdf = reportService.createReport(
                lastInvoices,
                formattedTextFieldStart.getText(),
                formattedTextFieldEnd.getText());
        pdfViewerService.openPdf(pdf);
    }

    private void initDateFields(){
        initDateField(formattedTextFieldStart);
        initDateField(formattedTextFieldEnd);
    }

    private void initDateField(JFormattedTextField field){
        var rawText = field.getText();
        if (rawText.replaceAll("[^a-zA-Z0-9]", "").isEmpty()) {
            val now = LocalDateTime.now();
            field.setValue(LocalDateTimeUtil.formatDateTime(now));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel1 = new JPanel();
        panel8 = new JPanel();
        label = new JLabel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel2 = new JPanel();
        Inicio = new JLabel();
        formattedTextFieldStart = new JFormattedTextField();
        panel3 = new JPanel();
        Inicio2 = new JLabel();
        formattedTextFieldEnd = new JFormattedTextField();
        panel11 = new JPanel();
        Inicio3 = new JLabel();
        comboBoxPaymentType = new JComboBox<>();
        panel7 = new JPanel();
        panel10 = new JPanel();
        label2 = new JLabel();
        Imprimir = new JButton();
        panel4 = new JPanel();
        label1 = new JLabel();
        button = new JButton();
        panel9 = new JPanel();

        //======== this ========
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setBorder(new EmptyBorder(5, 5, 5, 5));
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new VerticalLayout());

            //======== panel8 ========
            {
                panel8.setLayout(new FlowLayout());
                panel8.add(label);
            }
            panel1.add(panel8);

            //======== panel5 ========
            {
                panel5.setLayout(new BorderLayout(0, 10));

                //======== panel6 ========
                {
                    panel6.setLayout(new HorizontalLayout(5));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new VerticalLayout());

                        //---- Inicio ----
                        Inicio.setText("Inicio:");
                        panel2.add(Inicio);

                        //---- formattedTextFieldStart ----
                        formattedTextFieldStart.setPreferredSize(new Dimension(150, 25));
                        panel2.add(formattedTextFieldStart);
                    }
                    panel6.add(panel2);

                    //======== panel3 ========
                    {
                        panel3.setLayout(new VerticalLayout());

                        //---- Inicio2 ----
                        Inicio2.setText("Final:");
                        panel3.add(Inicio2);

                        //---- formattedTextFieldEnd ----
                        formattedTextFieldEnd.setPreferredSize(new Dimension(150, 25));
                        panel3.add(formattedTextFieldEnd);
                    }
                    panel6.add(panel3);

                    //======== panel11 ========
                    {
                        panel11.setLayout(new VerticalLayout());

                        //---- Inicio3 ----
                        Inicio3.setText("Tipo Pagamento:");
                        panel11.add(Inicio3);
                        panel11.add(comboBoxPaymentType);
                    }
                    panel6.add(panel11);
                }
                panel5.add(panel6, BorderLayout.WEST);

                //======== panel7 ========
                {
                    panel7.setLayout(new HorizontalLayout(5));

                    //======== panel10 ========
                    {
                        panel10.setLayout(new VerticalLayout());

                        //---- label2 ----
                        label2.setText("Imprimir");
                        panel10.add(label2);

                        //---- Imprimir ----
                        Imprimir.setText("Imprimir");
                        Imprimir.addActionListener(e -> printActionListener(e));
                        panel10.add(Imprimir);
                    }
                    panel7.add(panel10);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new VerticalLayout());

                        //---- label1 ----
                        label1.setText("Buscar:");
                        panel4.add(label1);

                        //---- button ----
                        button.setText("Consultar");
                        button.addActionListener(e -> buttonActionListener(e));
                        panel4.add(button);
                    }
                    panel7.add(panel4);
                }
                panel5.add(panel7, BorderLayout.EAST);

                //======== panel9 ========
                {
                    panel9.setLayout(new VerticalLayout());
                }
                panel5.add(panel9, BorderLayout.CENTER);
            }
            panel1.add(panel5);
        }
        add(panel1, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel1;
    private JPanel panel8;
    private JLabel label;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel2;
    private JLabel Inicio;
    private JFormattedTextField formattedTextFieldStart;
    private JPanel panel3;
    private JLabel Inicio2;
    private JFormattedTextField formattedTextFieldEnd;
    private JPanel panel11;
    private JLabel Inicio3;
    private JComboBox<ComboBoxOption> comboBoxPaymentType;
    private JPanel panel7;
    private JPanel panel10;
    private JLabel label2;
    private JButton Imprimir;
    private JPanel panel4;
    private JLabel label1;
    private JButton button;
    private JPanel panel9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
