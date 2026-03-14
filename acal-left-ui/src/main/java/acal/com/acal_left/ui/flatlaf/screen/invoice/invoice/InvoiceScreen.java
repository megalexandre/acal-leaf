package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.model.filter.PersonFilter;
import acal.com.acal_left.core.usecase.address.AddressFindAllUseCase;
import acal.com.acal_left.core.usecase.invoice.InvoiceDeleteUseCase;
import acal.com.acal_left.core.usecase.invoice.InvoiceListUseCase;
import acal.com.acal_left.core.usecase.invoice.InvoicePaginateUseCase;
import acal.com.acal_left.core.usecase.invoice.InvoicePayUseCase;
import acal.com.acal_left.core.usecase.person.PersonFindUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxLoader;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxOption;
import acal.com.acal_left.ui.flatlaf.component.render.StatusBadgeRenderer;
import acal.com.acal_left.ui.flatlaf.component.render.YesNoComboBoxRenderer;
import acal.com.acal_left.ui.flatlaf.component.utils.SwingUtils;
import acal.com.acal_left.ui.flatlaf.screen.invoice.create.InvoiceCreateDialog;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceScreenData;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableContent;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableModel;
import acal.com.acal_left.ui.flatlaf.utils.Toast;
import acal.com.acal_left.ui.report.PdfViewerService;
import acal.com.acal_left.ui.report.ReportService;
import acal.com.acal_left.ui.report.out.InvoiceReportOutput;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Component
@Scope("prototype")
@SuppressWarnings("FieldCanBeLocal")
public class InvoiceScreen extends JPanel {
    public final String name = Screen.INVOICE.name();

    @Autowired
    private PersonFindUseCase personFind;

    @Autowired
    private AddressFindAllUseCase addressFind;

    @Autowired
    private InvoicePaginateUseCase paginate;

    @Autowired
    private InvoiceListUseCase list;

    @Autowired
    private InvoiceDeleteUseCase delete;

    @Autowired
    private InvoicePayUseCase pay;

    private final InvoiceScreenData screenData = new InvoiceScreenData();

    private int currentPage = 0;
    private final int pageSize = 100;
    private boolean hasMorePages = false;
    private int totalPages = 0;

    public InvoiceScreen() {
        initComponents();
        initEvents();
        SwingUtils.applyNumericFilter(textFieldId);
    }

    private void initEvents() {
        createMask(formattedPeriod, "##/####");
        createMask(formattedDueDate, "##/##/####");

        ComboBoxLoader.setupLazyLoad(comboBoxPartner, this::getOrLoadPersons);
        ComboBoxLoader.setupLazyLoad(comboBoxAddress, this::getOrLoadAddresses);
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != -1) {
                    table.setRowSelectionInterval(row, row);
                    Invoice invoice = ((InvoiceTableModel) table.getModel()).get(row);
                    popupMenu.putClientProperty("selected", invoice);
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        Invoice invoice = ((InvoiceTableModel) table.getModel()).get(row);

                        new PdfViewerService().openPdf(
                            new ReportService().createReport(
                                    List.of(InvoiceReportOutput.fromDomain(invoice))
                            )
                        );

                    }
                }
            }
        });

        comboBoxStatus.setModel(new DefaultComboBoxModel<>(new Boolean[]{null, TRUE, FALSE}));
        comboBoxStatus.setSelectedItem(null);
        comboBoxStatus.setRenderer(new YesNoComboBoxRenderer());
    }

    private void invoiceViewActionListener(ActionEvent e) {
        createDialog((Invoice) popupMenu.getClientProperty("selected"));
    }

    private void invoicePrintActionListener(ActionEvent e) {
        Invoice i = (Invoice) popupMenu.getClientProperty("selected");

        new PdfViewerService().openPdf(
            new ReportService().createReport(List.of(InvoiceReportOutput.fromDomain(i)))
        );
    }

    private void payActionListener(ActionEvent e) {
        Invoice i = (Invoice) popupMenu.getClientProperty("selected");
        if(i.isPaid()){
            Toast.show(this, "Esse isso já foi recebido, ignorando a solicitação.");
            return;
        }

        i.setPaidAt(LocalDateTime.now());
        pay.execute(i);
        Toast.show(this, "Recebido");
        search(0);
    }

    private void deleteActionListener(ActionEvent e) {
        Invoice i = (Invoice) popupMenu.getClientProperty("selected");
        delete.execute(i);
        search(0);
    }

    private void printActionListener(ActionEvent e) {
        List<Invoice> invoices = list.execute(buildQuery());
        List<InvoiceReportOutput> invoicesToReport = invoices.stream().map(InvoiceReportOutput::fromDomain).toList();

        new PdfViewerService().openPdf(
            new ReportService().createReport(invoicesToReport)
        );
    }

    private void createDialog(Invoice invoice) {
        Window window = SwingUtilities.getWindowAncestor(this);
        InvoiceCreateDialog createDialog = new InvoiceCreateDialog(window, invoice);
        createDialog.pack();
        createDialog.setLocationRelativeTo(window);
        createDialog.setVisible(true);
    }


    private List<ComboBoxOption> getOrLoadPersons() {
        if (screenData.getPersons().isEmpty()) {
            screenData.setPersons(personFind.execute(new PersonFilter()));
        }
        return Arrays.asList(screenData.getPersonsOptions());
    }

    private List<ComboBoxOption> getOrLoadAddresses() {
        if (screenData.getAddresses().isEmpty()) {
            screenData.setAddresses(addressFind.execute());
        }
        return Arrays.asList(screenData.getAddressesOptions());
    }

    private void searchActionListener(ActionEvent e) {
        search(0);
    }

    private void search(int page){
        currentPage = page;
        fetchPageData();
    }

    private void clearActionListener(ActionEvent e) {
        textFieldId.setText("");
        formattedPeriod.setText("");
        formattedDueDate.setText("");
        comboBoxPartner.setSelectedIndex(0);
        comboBoxAddress.setSelectedIndex(0);

        search(0);
    }


    private void updatePaginationLabel() {
        InvoiceTableModel model = (InvoiceTableModel) table.getModel();
        table.setDefaultRenderer(Invoice.Status.class, new StatusBadgeRenderer());
        int itemsInThisPage = model.getItems().size();

        labelPagination.setText(String.format("Página %d de %d (%d itens)",
            currentPage + 1, totalPages, itemsInThisPage));

        buttonFirstPage.setEnabled(currentPage > 0);
        buttonPreviousPage.setEnabled(currentPage > 0);
        buttonNextPage.setEnabled(hasMorePages);
        buttonLastPage.setEnabled(hasMorePages && currentPage < totalPages - 1);
    }

    private void onFirstPage(ActionEvent e) {
        if (currentPage > 0) {
            currentPage = 0;
            fetchPageData();
        }
    }

    private void onPreviousPage(ActionEvent e) {
        if (currentPage > 0) {
            currentPage--;
            fetchPageData();
        }
    }

    private void onNextPage(ActionEvent e) {
        if (hasMorePages) {
            currentPage++;
            fetchPageData();
        }
    }

    private void onLastPage(ActionEvent e) {
        if (hasMorePages && currentPage < totalPages - 1) {
            currentPage = totalPages - 1;
            fetchPageData();
        }
    }

    private void fetchPageData() {
        Page<InvoiceTableContent> page =
            paginate.execute(buildQuery())
            .map(InvoiceTableContent::new);

        hasMorePages = page.hasNext();
        totalPages = page.getTotalPages();

        var items = page.getContent();

        InvoiceTableModel model = new InvoiceTableModel();
        model.setList(items);
        table.setModel(model);

        table.setDefaultRenderer(Invoice.Status.class, new StatusBadgeRenderer());
        updatePaginationLabel();
    }

    private InvoiceQuery buildQuery(){
        var pageable = PageRequest.of(currentPage, pageSize);
        return InvoiceQuery.builder()
                .id(getId())
                .personId(getPersonId())
                .paid(getPaid())
                .period(getPeriod())
                .dueDate(getDueDate())
                .addressId(getAddressId())
                .pageable(pageable).build();
    }

    private Integer getId(){
        return textFieldId.getText().isBlank() ? null : Integer.valueOf(textFieldId.getText());
    }

    private Integer getPersonId(){
        return ComboBoxOption.getSelectedId(comboBoxPartner);
    }

    private Integer getAddressId(){
        return ComboBoxOption.getSelectedId(comboBoxAddress);
    }

    private Boolean getPaid(){
        return (Boolean) comboBoxStatus.getSelectedItem();
    }

    private LocalDateTime getDueDate(){
        String texto = formattedDueDate.getText().trim();

        if (texto.contains("_") || texto.length() < 10) {
            return null;
        }

        try {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(texto, parser).atStartOfDay();
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private LocalDate getPeriod() {
        String texto = formattedPeriod.getText().trim();

        if (texto.contains("_") || texto.length() < 7) {
            return null;
        }

        try {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth mesAno = YearMonth.parse(texto, parser);
            return mesAno.atDay(1);

        } catch (DateTimeParseException e) {
            return null;
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





    @SuppressWarnings("Convert2MethodRef")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel1 = new JPanel();
        panel4 = new JPanel();
        buttonFirstPage = new JButton();
        buttonPreviousPage = new JButton();
        labelPagination = new JLabel();
        buttonNextPage = new JButton();
        buttonLastPage = new JButton();
        panel3 = new JPanel();
        panel5 = new JPanel();
        label1 = new JLabel();
        textFieldId = new JTextField();
        panel6 = new JPanel();
        label2 = new JLabel();
        comboBoxPartner = new JComboBox<>();
        panel10 = new JPanel();
        label6 = new JLabel();
        comboBoxStatus = new JComboBox<>();
        panel7 = new JPanel();
        label3 = new JLabel();
        comboBoxAddress = new JComboBox<>();
        panel8 = new JPanel();
        label4 = new JLabel();
        formattedPeriod = new JFormattedTextField();
        panel9 = new JPanel();
        label5 = new JLabel();
        formattedDueDate = new JFormattedTextField();
        panel2 = new JPanel();
        buttonClear = new JButton();
        buttonPrint = new JButton();
        buttonSearch = new JButton();
        popupMenu = new JPopupMenu();
        menuItemDelete = new JMenuItem();
        menuItemPay = new JMenuItem();
        menuItemInvoicePrint = new JMenuItem();

        //======== this ========
        setMinimumSize(new Dimension(1024, 768));
        setPreferredSize(new Dimension(1024, 768));
        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new VerticalLayout());

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout());

                //---- buttonFirstPage ----
                buttonFirstPage.setText("<<");
                buttonFirstPage.addActionListener(e -> onFirstPage(e));
                panel4.add(buttonFirstPage);

                //---- buttonPreviousPage ----
                buttonPreviousPage.setText("<");
                buttonPreviousPage.addActionListener(e -> onPreviousPage(e));
                panel4.add(buttonPreviousPage);
                panel4.add(labelPagination);

                //---- buttonNextPage ----
                buttonNextPage.setText(">");
                buttonNextPage.addActionListener(e -> onNextPage(e));
                panel4.add(buttonNextPage);

                //---- buttonLastPage ----
                buttonLastPage.setText(">>");
                buttonLastPage.addActionListener(e -> onLastPage(e));
                panel4.add(buttonLastPage);
            }
            panel1.add(panel4);

            //======== panel3 ========
            {
                panel3.setBorder(new EtchedBorder());
                panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                //======== panel5 ========
                {
                    panel5.setPreferredSize(new Dimension(150, 44));
                    panel5.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("N\u00famero:");
                    panel5.add(label1);
                    panel5.add(textFieldId);
                }
                panel3.add(panel5);

                //======== panel6 ========
                {
                    panel6.setPreferredSize(new Dimension(150, 44));
                    panel6.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("S\u00f3cio:");
                    panel6.add(label2);
                    panel6.add(comboBoxPartner);
                }
                panel3.add(panel6);

                //======== panel10 ========
                {
                    panel10.setPreferredSize(new Dimension(150, 44));
                    panel10.setLayout(new VerticalLayout());

                    //---- label6 ----
                    label6.setText("Pago:");
                    panel10.add(label6);
                    panel10.add(comboBoxStatus);
                }
                panel3.add(panel10);

                //======== panel7 ========
                {
                    panel7.setPreferredSize(new Dimension(150, 44));
                    panel7.setLayout(new VerticalLayout());

                    //---- label3 ----
                    label3.setText("Endere\u00e7o:");
                    panel7.add(label3);
                    panel7.add(comboBoxAddress);
                }
                panel3.add(panel7);

                //======== panel8 ========
                {
                    panel8.setPreferredSize(new Dimension(150, 44));
                    panel8.setLayout(new VerticalLayout());

                    //---- label4 ----
                    label4.setText("Compet\u00eancia:");
                    panel8.add(label4);
                    panel8.add(formattedPeriod);
                }
                panel3.add(panel8);

                //======== panel9 ========
                {
                    panel9.setLayout(new VerticalLayout());

                    //---- label5 ----
                    label5.setText("Vencimento:");
                    label5.setPreferredSize(new Dimension(150, 19));
                    panel9.add(label5);
                    panel9.add(formattedDueDate);
                }
                panel3.add(panel9);
            }
            panel1.add(panel3);

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- buttonClear ----
                buttonClear.setText("Limpar");
                buttonClear.addActionListener(e -> clearActionListener(e));
                panel2.add(buttonClear);

                //---- buttonPrint ----
                buttonPrint.setText("Imprimir");
                buttonPrint.addActionListener(e -> printActionListener(e));
                panel2.add(buttonPrint);

                //---- buttonSearch ----
                buttonSearch.setText("Consultas");
                buttonSearch.addActionListener(e -> searchActionListener(e));
                panel2.add(buttonSearch);
            }
            panel1.add(panel2);
        }
        add(panel1, BorderLayout.SOUTH);

        //======== popupMenu ========
        {

            //---- menuItemDelete ----
            menuItemDelete.setText("Deletar");
            menuItemDelete.addActionListener(e -> deleteActionListener(e));
            popupMenu.add(menuItemDelete);

            //---- menuItemPay ----
            menuItemPay.setText("Receber");
            menuItemPay.addActionListener(e -> payActionListener(e));
            popupMenu.add(menuItemPay);
            popupMenu.addSeparator();

            //---- menuItemInvoicePrint ----
            menuItemInvoicePrint.setText("Imprimir");
            menuItemInvoicePrint.addActionListener(e -> invoicePrintActionListener(e));
            popupMenu.add(menuItemInvoicePrint);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel1;
    private JPanel panel4;
    private JButton buttonFirstPage;
    private JButton buttonPreviousPage;
    private JLabel labelPagination;
    private JButton buttonNextPage;
    private JButton buttonLastPage;
    private JPanel panel3;
    private JPanel panel5;
    private JLabel label1;
    private JTextField textFieldId;
    private JPanel panel6;
    private JLabel label2;
    private JComboBox<ComboBoxOption> comboBoxPartner;
    private JPanel panel10;
    private JLabel label6;
    private JComboBox<Boolean> comboBoxStatus;
    private JPanel panel7;
    private JLabel label3;
    private JComboBox<ComboBoxOption> comboBoxAddress;
    private JPanel panel8;
    private JLabel label4;
    private JFormattedTextField formattedPeriod;
    private JPanel panel9;
    private JLabel label5;
    private JFormattedTextField formattedDueDate;
    private JPanel panel2;
    private JButton buttonClear;
    private JButton buttonPrint;
    private JButton buttonSearch;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemDelete;
    private JMenuItem menuItemPay;
    private JMenuItem menuItemInvoicePrint;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
