package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice;

import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.usecase.invoice.InvoiceFindUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableContent;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableModel;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.render.InvoiceTableRender;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
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
    private int currentPage = 0;
    private int pageSize = 100;
    private boolean hasMorePages = false;

    public InvoiceScreen(InvoiceFindUseCase find) {
        this.find = find;
        initComponents();
    }

    private void searchActionListener(ActionEvent e) {
        currentPage = 0;
        fetchPageData();
    }

    private void updatePaginationLabel() {
        InvoiceTableModel model = (InvoiceTableModel) table.getModel();
        int itemsInThisPage = model.getItems().size();

        labelPagination.setText(String.format("Página %d (%d itens)", currentPage + 1, itemsInThisPage));

        buttonFirstPage.setEnabled(currentPage > 0);
        buttonPreviousPage.setEnabled(currentPage > 0);
        buttonNextPage.setEnabled(hasMorePages);
        buttonLastPage.setEnabled(hasMorePages);
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
        if (hasMorePages) {
            currentPage++;
            fetchPageData();
        }
    }

    private void fetchPageData() {
        var pageable = PageRequest.of(currentPage, pageSize);
        var items = find.execute(InvoiceQuery.builder().pageable(pageable).build())
                .stream()
                .map(InvoiceTableContent::new)
                .toList();

        hasMorePages = items.size() >= pageSize;

        InvoiceTableModel model = new InvoiceTableModel();
        model.setList(items);
        table.setModel(model);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new InvoiceTableRender());
        }

        updatePaginationLabel();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        panel4 = new JPanel();
        buttonFirstPage = new JButton();
        buttonPreviousPage = new JButton();
        labelPagination = new JLabel();
        buttonNextPage = new JButton();
        buttonLastPage = new JButton();
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

            //======== panel4 ========
            {
                panel4.setBorder(new CompoundBorder(
                    new TitledBorder("Pagina\u00e7\u00e3o"),
                    new EmptyBorder(5, 5, 5, 5)));
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
    private JPanel panel1;
    private JPanel panel4;
    private JButton buttonFirstPage;
    private JButton buttonPreviousPage;
    private JLabel labelPagination;
    private JButton buttonNextPage;
    private JButton buttonLastPage;
    private JPanel panel3;
    private JPanel panel2;
    private JButton buttonSearch;
    private JScrollPane scrollPane1;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
