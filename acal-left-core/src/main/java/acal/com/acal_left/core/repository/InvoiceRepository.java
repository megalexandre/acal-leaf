package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.core.model.filter.InvoiceQuery;

import java.util.List;

public interface InvoiceRepository {
    List<Invoice> findInvoices(InvoiceQuery invoiceQuery);
}
