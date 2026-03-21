package acal.com.acal_left.ui.flatlaf.screen.invoice.receiver;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceFilter;
import acal.com.acal_left.core.usecase.invoice.InvoiceListUseCase;
import acal.com.acal_left.core.usecase.invoice.InvoicePayUseCase;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import acal.com.acal_left.shared.LocalDateUtil;
import acal.com.acal_left.ui.flatlaf.component.utils.SwingUtils;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class ReceiverInvoicePayment extends JDialog {

    @Setter
    private ActionListener onPay;

    @Autowired
    private InvoiceListUseCase listUseCase;

    @Autowired
    private InvoicePayUseCase pay ;

    private Invoice invoice;

    public ReceiverInvoicePayment(Window owner) {
        super(owner);
        initComponents();
        init();
    }

    private void init() {
        setupEscapeKey();

        textFieldPeriod.setEnabled(false);
        textFieldPartner.setEnabled(false);
        textFieldValue.setEnabled(false);
        checkBoxToday.setEnabled(false);
        checkBoxPaidByPix.setEnabled(false);
        checkBoxAlternativeBill.setEnabled(false);
        formattedTextFieldPayedAt.setEnabled(false);

        createMask(formattedTextFieldPayedAt, "##/##/####");
        SwingUtils.applyNumericFilter(textFieldNumber);
        okButton.setEnabled(false);

        checkBoxToday.addActionListener(e -> {
            formattedTextFieldPayedAt.setText(
                LocalDateTimeUtil.formatDateTime(LocalDateTime.now())
           );
        });

        okButton.addActionListener(e -> {
            pay();
        });
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        textFieldNumber.setText(invoice.getId().toString());
        textFieldNumber.setEnabled(false);

        loadInvoiceData(invoice);
    }

    private void loadInvoiceData(Invoice item) {
        textFieldPartner.setText(item.getPerson().getName());
        textFieldValue.setText(BigDecimalUtil.toBRL(item.totalAmount()));
        textFieldPeriod.setText(LocalDateUtil.formatPeriod(item.getPeriod()));

        checkBoxPaidByPix.setSelected(item.isPaidByPix());
        checkBoxAlternativeBill.setSelected(item.isPaidWithAlternativeBill());

        if (item.isPaid()) {
            formattedTextFieldPayedAt.setText(LocalDateTimeUtil.formatDateTime(item.getPaidAt()));
        } else {
            checkBoxToday.setEnabled(true);
            formattedTextFieldPayedAt.setEnabled(true);
            checkBoxPaidByPix.setEnabled(true);
            checkBoxAlternativeBill.setEnabled(true);
            okButton.setEnabled(true);
        }
    }

    private void search() {
        clearFields();
        var q = InvoiceFilter.builder().id(getNumber()).build();
        List<Invoice> list = listUseCase.execute(q);

        if(!list.isEmpty()) {
            var item = list.getFirst();
            this.invoice = item;
            textFieldPartner.setText(item.getPerson().getName());
            textFieldValue.setText(BigDecimalUtil.toBRL(item.totalAmount()));
            textFieldPeriod.setText(LocalDateUtil.formatPeriod(item.getPeriod()));

            checkBoxPaidByPix.setSelected(invoice.isPaidByPix());
            checkBoxAlternativeBill.setSelected(invoice.isPaidWithAlternativeBill());

            if(item.isPaid()){
                formattedTextFieldPayedAt.setText(LocalDateTimeUtil.formatDateTime(item.getPaidAt()));
            }else {
                checkBoxToday.setEnabled(true);
                formattedTextFieldPayedAt.setEnabled(true);
                checkBoxPaidByPix.setEnabled(true);
                checkBoxAlternativeBill.setEnabled(true);

                okButton.setEnabled(true);
            }
        }
    }
    private void pay() {
        invoice.setPaidAt(getPayedAt());
        invoice.setPaidWithAlternativeBill(isPaidWithAlternativeBill());
        invoice.setPaidByPix(isPaidByPix());
        pay.execute(invoice);
        if (onPay != null) {
            onPay.actionPerformed(new java.awt.event.ActionEvent(this, java.awt.event.ActionEvent.ACTION_PERFORMED, "paid"));
        }
        dispose();
    }

    private void clearFields() {
        textFieldPartner.setText("");
        textFieldValue.setText("");
        textFieldPeriod.setText("");
        checkBoxToday.setEnabled(false);
        formattedTextFieldPayedAt.setEnabled(false);
        formattedTextFieldPayedAt.setText("");
        okButton.setEnabled(false);
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

    private Boolean isPaidByPix() {
        return checkBoxPaidByPix.isSelected();
    }

    private Boolean isPaidWithAlternativeBill() {
        return checkBoxAlternativeBill.isSelected();
    }

    private void textFieldNumberKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }

    private LocalDateTime getPayedAt() {
        var text = formattedTextFieldPayedAt.getText();
        return LocalDateTimeUtil.fromString(text);
    }

    private Integer getNumber() {
        var text = textFieldNumber.getText();
        if(Objects.equals(text, "")){
            text = "0";
        }

        return Integer.valueOf(text);
    }

    private void cancelActionListener(ActionEvent e) {
        this.dispose();
    }

    private void setupEscapeKey() {
        String dispatchWindowActionMapKey = "com.acal.dispatchWindowActionMapKey";
        getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), dispatchWindowActionMapKey);

        getRootPane().getActionMap().put(dispatchWindowActionMapKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel2 = new JPanel();
        label2 = new JLabel();
        textFieldNumber = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        textFieldPeriod = new JTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        textFieldPartner = new JTextField();
        panel5 = new JPanel();
        Valor = new JLabel();
        textFieldValue = new JTextField();
        panel1 = new JPanel();
        label1 = new JLabel();
        panel6 = new JPanel();
        label5 = new JLabel();
        panel7 = new JPanel();
        checkBoxToday = new JCheckBox();
        formattedTextFieldPayedAt = new JFormattedTextField();
        panel8 = new JPanel();
        checkBoxPaidByPix = new JCheckBox();
        checkBoxAlternativeBill = new JCheckBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setPreferredSize(new Dimension(540, 300));
            dialogPane.setBorder(new EmptyBorder(10, 10, 10, 10));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPanel.setLayout(new MigLayout(
                    "fillx,insets 0,novisualpadding,hidemode 3,align center center,gap 10 10",
                    // columns
                    "[fill, 50%!]",
                    // rows
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]"));

                //======== panel2 ========
                {
                    panel2.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("N\u00famero");
                    panel2.add(label2);

                    //---- textFieldNumber ----
                    textFieldNumber.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            textFieldNumberKeyPressed(e);
                        }
                    });
                    panel2.add(textFieldNumber);
                }
                contentPanel.add(panel2, "cell 0 0");

                //======== panel3 ========
                {
                    panel3.setLayout(new VerticalLayout());

                    //---- label3 ----
                    label3.setText("Refer\u00eancia:");
                    panel3.add(label3);
                    panel3.add(textFieldPeriod);
                }
                contentPanel.add(panel3, "cell 1 0");

                //======== panel4 ========
                {
                    panel4.setLayout(new VerticalLayout());

                    //---- label4 ----
                    label4.setText("S\u00f3cio:");
                    panel4.add(label4);
                    panel4.add(textFieldPartner);
                }
                contentPanel.add(panel4, "cell 0 1");

                //======== panel5 ========
                {
                    panel5.setLayout(new VerticalLayout());

                    //---- Valor ----
                    Valor.setText("Valor:");
                    panel5.add(Valor);
                    panel5.add(textFieldValue);
                }
                contentPanel.add(panel5, "cell 1 1");

                //======== panel1 ========
                {
                    panel1.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("Receber:");
                    panel1.add(label1);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new HorizontalLayout());

                        //---- label5 ----
                        label5.setText("Data de Pagamento:");
                        panel6.add(label5);
                    }
                    panel1.add(panel6);
                }
                contentPanel.add(panel1, "cell 0 2");

                //======== panel7 ========
                {
                    panel7.setLayout(new VerticalLayout());

                    //---- checkBoxToday ----
                    checkBoxToday.setText("Hoje:");
                    panel7.add(checkBoxToday);
                    panel7.add(formattedTextFieldPayedAt);
                }
                contentPanel.add(panel7, "cell 1 2");

                //======== panel8 ========
                {
                    panel8.setLayout(new VerticalLayout());

                    //---- checkBoxPaidByPix ----
                    checkBoxPaidByPix.setText("Pago via pix?");
                    panel8.add(checkBoxPaidByPix);
                }
                contentPanel.add(panel8, "cell 0 3");

                //---- checkBoxAlternativeBill ----
                checkBoxAlternativeBill.setText("Pago com Segunda via?");
                contentPanel.add(checkBoxAlternativeBill, "cell 1 3");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Confirmar");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelActionListener(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textFieldNumber;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textFieldPeriod;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textFieldPartner;
    private JPanel panel5;
    private JLabel Valor;
    private JTextField textFieldValue;
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel6;
    private JLabel label5;
    private JPanel panel7;
    private JCheckBox checkBoxToday;
    private JFormattedTextField formattedTextFieldPayedAt;
    private JPanel panel8;
    private JCheckBox checkBoxPaidByPix;
    private JCheckBox checkBoxAlternativeBill;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
