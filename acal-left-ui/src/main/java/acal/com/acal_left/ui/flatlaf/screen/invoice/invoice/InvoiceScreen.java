package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice;

import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.usecase.invoice.InvoiceFindUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.InvoiceTableContent;
import acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model.PaginatedInvoiceTableModel;
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
    private PaginatedInvoiceTableModel paginatedModel;
    private boolean hasMorePages = false;

    public InvoiceScreen(InvoiceFindUseCase find) {
        this.find = find;
        initComponents();
    }

    private void searchActionListener(ActionEvent e) {
        paginatedModel = new PaginatedInvoiceTableModel();
        table.setModel(paginatedModel);
        fetchPageData();
    }

    private void updatePaginationLabel() {
        if (paginatedModel != null) {
            int currentPage = paginatedModel.getPagination().getCurrentPage() + 1;
            int pageSize = paginatedModel.getPagination().getPageSize();
            int itemsInThisPage = paginatedModel.getPageItems().size();

            int estimatedTotal = itemsInThisPage < pageSize ?
                (paginatedModel.getPagination().getCurrentPage() * pageSize) + itemsInThisPage :
                (paginatedModel.getPagination().getCurrentPage() + 1) * pageSize;

            int totalPages = (int) Math.ceil((double) estimatedTotal / pageSize);

            labelPagination.setText(String.format("Página %d de %d (%d itens)", currentPage, Math.max(totalPages, 1), estimatedTotal));

            // Habilitar/desabilitar botões
            buttonFirstPage.setEnabled(paginatedModel.getPagination().getCurrentPage() > 0);
            buttonPreviousPage.setEnabled(paginatedModel.getPagination().hasPreviousPage());
            buttonNextPage.setEnabled(hasMorePages);
            buttonLastPage.setEnabled(hasMorePages);
        }
    }

    private void onFirstPage(ActionEvent e) {
        if (paginatedModel != null && paginatedModel.getPagination().getCurrentPage() > 0) {
            paginatedModel.firstPage();
            fetchPageData();
        }
    }

    private void onPreviousPage(ActionEvent e) {
        if (paginatedModel != null && paginatedModel.getPagination().hasPreviousPage()) {
            paginatedModel.previousPage();
            fetchPageData();
        }
    }

    private void onNextPage(ActionEvent e) {
        if (paginatedModel != null && paginatedModel.getPagination().hasNextPage()) {
            paginatedModel.nextPage();
            fetchPageData();
        }
    }

    private void onLastPage(ActionEvent e) {
        if (paginatedModel != null && paginatedModel.getPagination().hasNextPage()) {
            paginatedModel.lastPage();
            fetchPageData();
        }
    }

    private void fetchPageData() {
        int currentPage = paginatedModel.getPagination().getCurrentPage();
        int pageSize = paginatedModel.getPagination().getPageSize();
        var pageable = PageRequest.of(currentPage, pageSize);
        var items = find.execute(InvoiceQuery.builder().pageable(pageable).build()).stream().map(InvoiceTableContent::new).toList();

        hasMorePages = items.size() >= pageSize;

        paginatedModel.setList(items);
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
