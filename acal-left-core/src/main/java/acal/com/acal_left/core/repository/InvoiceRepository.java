package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceGenerateFilter;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceRepository {

    Invoice pay(Invoice invoice);
    List<Invoice> listInvoices(InvoiceQuery invoiceQuery);
    List<Invoice> listInvoicesToGenerate(InvoiceGenerateFilter filter);
    Page<Invoice> paginateInvoices(InvoiceQuery invoiceQuery);
    void delete(Invoice invoice);
    List<Invoice> save(List<Invoice> invoices);
}
