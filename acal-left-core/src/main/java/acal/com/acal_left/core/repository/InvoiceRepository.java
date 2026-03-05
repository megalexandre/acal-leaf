package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;
import org.springframework.data.domain.Page;

public interface InvoiceRepository {
    Page<Invoice> findInvoices(InvoiceQuery invoiceQuery);
}
