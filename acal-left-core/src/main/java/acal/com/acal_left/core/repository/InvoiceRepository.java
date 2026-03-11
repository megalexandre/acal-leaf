package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceRepository {

    List<Invoice> listInvoices(InvoiceQuery invoiceQuery);
    Page<Invoice> paginateInvoices(InvoiceQuery invoiceQuery);
    void delete(Invoice invoice);
}
