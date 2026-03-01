
package acal.com.acal_left.ui.screen.search;

import acal.com.acal_left.core.event.ChangeScreenEvent;
import acal.com.acal_left.core.event.Screen;
import acal.com.acal_left.core.model.InvoiceQuery;
import acal.com.acal_left.core.usecase.AddressFindAllUseCase;
import acal.com.acal_left.core.usecase.CategoryFindAllUseCase;
import acal.com.acal_left.core.usecase.InvoiceCreateReportUseCase;
import acal.com.acal_left.core.usecase.PartnerFindAllUseCase;
import acal.com.acal_left.model.Invoice;
import acal.com.acal_left.shared.StringUtil;
import acal.com.acal_left.ui.SwingUtils;
import acal.com.acal_left.ui.model.ComboBoxOption;
import acal.com.acal_left.ui.report.PdfViewerService;
import acal.com.acal_left.ui.report.ReportService;
import acal.com.acal_left.ui.report.out.InvoiceReportOutput;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;

import static acal.com.acal_left.ui.model.ComboBoxOption.getSelectedId;

@Component
public class InvoiceSearch extends JFrame {

    private final CategoryFindAllUseCase findAllCategory;
    private final PartnerFindAllUseCase findAllPartner;
    private final AddressFindAllUseCase findAllAddress;
    private final InvoiceCreateReportUseCase createReportInvoice;

    public InvoiceSearch(
            CategoryFindAllUseCase findAllCategory,
            PartnerFindAllUseCase findAllPartner,
            AddressFindAllUseCase findAllAddress,
            InvoiceCreateReportUseCase createReportInvoice
    ) {
        this.findAllCategory = findAllCategory;
        this.findAllPartner = findAllPartner;
        this.findAllAddress = findAllAddress;
        this.createReportInvoice = createReportInvoice;

        initComponents();
        SwingUtils.applyNumericFilter(textFieldInvoiceId);
    }

    @EventListener
    public void onScreenChange(ChangeScreenEvent event) {
        if(event.getScreen() != Screen.INVOICE_SEARCH) {
            clear();
            return;
        }
        startScreen();
        this.setVisible(true);
    }

    private void startScreen(){
        startComboBox();
    }

    private void startComboBox(){
        startComboBox(comboBoxStatus);
        startComboBox(comboBoxCategory);
        startComboBox(comboBoxPartner);
        startComboBox(comboBoxAddress);
        textFieldInvoiceId.setEnabled(false);
    }

    private void startComboBox(JComboBox<?> comboBox){
        comboBox.setEnabled(false);
        comboBox.setSelectedIndex(-1);
        comboBox.setModel(new DefaultComboBoxModel<>());
    }

    private void clear(){
        checkBoxStatus.setSelected(false);
        checkBoxCategory.setSelected(false);
        checkBoxPartner.setSelected(false);
        checkBoxAddress.setSelected(false);
        checkBoxInvoiceId.setSelected(false);
        textFieldInvoiceId.setText("");
    }

    private void checkBoxInvoiceIdItemStateChanged(ItemEvent e) {
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        textFieldInvoiceId.setEnabled(isSelected);
        if(!isSelected){
            textFieldInvoiceId.setText("");
        }
    }

    private void checkBoxStatusItemStateChanged(ItemEvent e) {
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        List<ComboBoxOption> itens = isSelected ? getStatus() : List.of();
        updateComboBoxState(comboBoxStatus, isSelected, itens);
    }

    private void checkBoxCategoryItemStateChanged(ItemEvent e) {
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        List<ComboBoxOption> itens = isSelected ? getCategories() : List.of();
        updateComboBoxState(comboBoxCategory, isSelected, itens);
    }

    private void checkBoxPartnerItemStateChanged(ItemEvent e) {
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        List<ComboBoxOption> itens = isSelected ? getPartners() : List.of();
        updateComboBoxState(comboBoxPartner, isSelected, itens);
    }

    private void checkBoxAddressItemStateChanged(ItemEvent e) {
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        List<ComboBoxOption> itens = isSelected ? getAddress() : List.of();
        updateComboBoxState(comboBoxAddress, isSelected, itens);
    }
    
    private List<ComboBoxOption> getStatus(){
        return List.of(
            new ComboBoxOption(1, "Aberta"),
            new ComboBoxOption(2, "Fechada")
        );
    }

    private List<ComboBoxOption> getPartners(){
        return findAllPartner.execute().stream().map(
            it -> new ComboBoxOption(it.getId(), it.getName())
        ).toList();
    }

    private List<ComboBoxOption> getCategories(){
        return findAllCategory.execute().stream().map(
            it -> new ComboBoxOption(it.getId(), it.getName())
        ).toList();
    }

    private List<ComboBoxOption> getAddress(){
        return findAllAddress.execute().stream().map(
                it -> new ComboBoxOption(it.getId(), it.getName())
        ).toList();
    }

    private void updateComboBoxState(JComboBox<ComboBoxOption> comboBox, boolean enabled, List<ComboBoxOption> options) {
        comboBox.setEnabled(enabled);
        if (enabled) {
            comboBox.setModel(new DefaultComboBoxModel<>(options.toArray(new ComboBoxOption[0])));
        } else {
            comboBox.setModel(new DefaultComboBoxModel<>());
            comboBox.setSelectedIndex(-1);
        }
    }

    private void cancelAction(ActionEvent e) {
        clear();
        dispose();
    }

    private void searchActionListener(ActionEvent e) {

        if(!isFilterSelected()){
            noOneFilterSelected();
            return;
        }

        try {

            List<InvoiceReportOutput> invoices = getData();

            List<InvoiceReportOutput> partnersOnly = getData().stream().filter(InvoiceReportOutput::getPartnerExclusive).toList();
            List<InvoiceReportOutput> partners = getData().stream().filter(InvoiceReportOutput::getNormalPartner).toList();

            if (invoices.isEmpty()) {
                showEmptyData();
                return;
            }

            if(!partnersOnly.isEmpty()) {
                var pdfPartnersOnly = new ReportService().createReport(partnersOnly);
                new PdfViewerService().openPdf(pdfPartnersOnly);
            }

            if(!partners.isEmpty()){
                var pdfPartners = new ReportService().createReport(partners);
                new PdfViewerService().openPdf(pdfPartners);
            }

            if(partnersOnly.isEmpty() && partners.isEmpty()){
                noDateFound();
            }

        } catch (Exception ex) {
            errorOnPrint(ex);
        }
    }

    private boolean isFilterSelected(){
        return checkBoxInvoiceId.isSelected() ||
                checkBoxStatus.isSelected() ||
                checkBoxCategory.isSelected() ||
                checkBoxPartner.isSelected() ||
                checkBoxAddress.isSelected();
    }

    private void noDateFound(){
        JOptionPane.showMessageDialog(
                this,
                "Nenhum dado encontrado.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void noOneFilterSelected(){
        JOptionPane.showMessageDialog(
                this,
                "Selecione ao menos um filtro para realizar a busca.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void errorOnPrint(Exception ex){
        JOptionPane.showMessageDialog(
                this,
                "Erro ao gerar relatório: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void showEmptyData(){
        JOptionPane.showMessageDialog(
                this,
                "Nenhuma conta encontrada com os filtros selecionados.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private List<InvoiceReportOutput> getData(){
        InvoiceQuery filter = new InvoiceQuery(
                StringUtil.toInteger(textFieldInvoiceId.getText()),
                getSelectedId(comboBoxCategory),
                getSelectedId(comboBoxAddress),
                getSelectedId(comboBoxPartner)
        );

        List<Invoice> invoices = createReportInvoice.execute(filter);
        return invoices.stream().map(InvoiceReportOutput::new).toList();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        panel6 = new JPanel();
        textFieldInvoiceId = new JTextField();
        checkBoxInvoiceId = new JCheckBox();
        panel2 = new JPanel();
        label2 = new JLabel();
        panel7 = new JPanel();
        comboBoxStatus = new JComboBox<>();
        checkBoxStatus = new JCheckBox();
        panel3 = new JPanel();
        label3 = new JLabel();
        panel8 = new JPanel();
        comboBoxCategory = new JComboBox<>();
        checkBoxCategory = new JCheckBox();
        panel4 = new JPanel();
        label4 = new JLabel();
        panel9 = new JPanel();
        comboBoxPartner = new JComboBox<>();
        checkBoxPartner = new JCheckBox();
        panel5 = new JPanel();
        label5 = new JLabel();
        panel10 = new JPanel();
        comboBoxAddress = new JComboBox<>();
        checkBoxAddress = new JCheckBox();
        buttonBar = new JPanel();
        cancelButton = new JButton();
        searchButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(1024, 768));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setBorder(new TitledBorder("Filtros:"));
                contentPanel.setLayout(new VerticalLayout(10));

                //======== panel1 ========
                {
                    panel1.setBorder(new EtchedBorder());
                    panel1.setLayout(new BorderLayout(10, 10));

                    //---- label1 ----
                    label1.setText("Conta N\u00famero:");
                    panel1.add(label1, BorderLayout.WEST);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new HorizontalLayout());

                        //---- textFieldInvoiceId ----
                        textFieldInvoiceId.setMinimumSize(new Dimension(300, 35));
                        textFieldInvoiceId.setPreferredSize(new Dimension(300, 35));
                        panel6.add(textFieldInvoiceId);

                        //---- checkBoxInvoiceId ----
                        checkBoxInvoiceId.addItemListener(e -> checkBoxInvoiceIdItemStateChanged(e));
                        panel6.add(checkBoxInvoiceId);
                    }
                    panel1.add(panel6, BorderLayout.EAST);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setBorder(new EtchedBorder());
                    panel2.setLayout(new BorderLayout(10, 10));

                    //---- label2 ----
                    label2.setText("Status:");
                    panel2.add(label2, BorderLayout.WEST);

                    //======== panel7 ========
                    {
                        panel7.setLayout(new HorizontalLayout());

                        //---- comboBoxStatus ----
                        comboBoxStatus.setMinimumSize(new Dimension(300, 35));
                        comboBoxStatus.setPreferredSize(new Dimension(300, 35));
                        panel7.add(comboBoxStatus);

                        //---- checkBoxStatus ----
                        checkBoxStatus.addItemListener(e -> checkBoxStatusItemStateChanged(e));
                        panel7.add(checkBoxStatus);
                    }
                    panel2.add(panel7, BorderLayout.EAST);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setBorder(new EtchedBorder());
                    panel3.setLayout(new BorderLayout(10, 10));

                    //---- label3 ----
                    label3.setText("Categoria:");
                    panel3.add(label3, BorderLayout.WEST);

                    //======== panel8 ========
                    {
                        panel8.setLayout(new HorizontalLayout());

                        //---- comboBoxCategory ----
                        comboBoxCategory.setMinimumSize(new Dimension(300, 35));
                        comboBoxCategory.setPreferredSize(new Dimension(300, 35));
                        panel8.add(comboBoxCategory);

                        //---- checkBoxCategory ----
                        checkBoxCategory.addItemListener(e -> checkBoxCategoryItemStateChanged(e));
                        panel8.add(checkBoxCategory);
                    }
                    panel3.add(panel8, BorderLayout.EAST);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setBorder(new EtchedBorder());
                    panel4.setLayout(new BorderLayout(10, 10));

                    //---- label4 ----
                    label4.setText("S\u00f3cio:");
                    panel4.add(label4, BorderLayout.WEST);

                    //======== panel9 ========
                    {
                        panel9.setLayout(new HorizontalLayout());

                        //---- comboBoxPartner ----
                        comboBoxPartner.setMinimumSize(new Dimension(300, 35));
                        comboBoxPartner.setPreferredSize(new Dimension(300, 35));
                        panel9.add(comboBoxPartner);

                        //---- checkBoxPartner ----
                        checkBoxPartner.addItemListener(e -> checkBoxPartnerItemStateChanged(e));
                        panel9.add(checkBoxPartner);
                    }
                    panel4.add(panel9, BorderLayout.EAST);
                }
                contentPanel.add(panel4);

                //======== panel5 ========
                {
                    panel5.setBorder(new EtchedBorder());
                    panel5.setLayout(new BorderLayout(10, 10));

                    //---- label5 ----
                    label5.setText("Logradouro:");
                    panel5.add(label5, BorderLayout.WEST);

                    //======== panel10 ========
                    {
                        panel10.setLayout(new HorizontalLayout());

                        //---- comboBoxAddress ----
                        comboBoxAddress.setMinimumSize(new Dimension(300, 35));
                        comboBoxAddress.setPreferredSize(new Dimension(300, 35));
                        panel10.add(comboBoxAddress);

                        //---- checkBoxAddress ----
                        checkBoxAddress.addItemListener(e -> checkBoxAddressItemStateChanged(e));
                        panel10.add(checkBoxAddress);
                    }
                    panel5.add(panel10, BorderLayout.EAST);
                }
                contentPanel.add(panel5);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setPreferredSize(new Dimension(100, 35));
                cancelButton.addActionListener(e -> cancelAction(e));
                buttonBar.add(cancelButton);

                //---- searchButton ----
                searchButton.setText("Buscar");
                searchButton.setPreferredSize(new Dimension(100, 35));
                searchButton.addActionListener(e -> searchActionListener(e));
                buttonBar.add(searchButton);
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel6;
    private JTextField textFieldInvoiceId;
    private JCheckBox checkBoxInvoiceId;
    private JPanel panel2;
    private JLabel label2;
    private JPanel panel7;
    private JComboBox<ComboBoxOption> comboBoxStatus;
    private JCheckBox checkBoxStatus;
    private JPanel panel3;
    private JLabel label3;
    private JPanel panel8;
    private JComboBox<ComboBoxOption> comboBoxCategory;
    private JCheckBox checkBoxCategory;
    private JPanel panel4;
    private JLabel label4;
    private JPanel panel9;
    private JComboBox<ComboBoxOption> comboBoxPartner;
    private JCheckBox checkBoxPartner;
    private JPanel panel5;
    private JLabel label5;
    private JPanel panel10;
    private JComboBox<ComboBoxOption> comboBoxAddress;
    private JCheckBox checkBoxAddress;
    private JPanel buttonBar;
    private JButton cancelButton;
    private JButton searchButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
