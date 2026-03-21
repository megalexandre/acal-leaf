package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceGenerateFilter;
import acal.com.acal_left.core.model.filter.InvoiceFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceRepository {

    Invoice pay(Invoice invoice);
    List<Invoice> listInvoices(InvoiceFilter invoiceFilter);
    List<Invoice> listInvoicesToGenerate(InvoiceGenerateFilter filter);
    Page<Invoice> paginateInvoices(InvoiceFilter invoiceFilter);
    void delete(Invoice invoice);
    List<Invoice> save(List<Invoice> invoices);
}
