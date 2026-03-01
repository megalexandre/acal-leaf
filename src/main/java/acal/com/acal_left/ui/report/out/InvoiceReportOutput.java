package acal.com.acal_left.ui.report.out;

import acal.com.acal_left.model.Invoice;

public class InvoiceReportOutput {

    private String partner = "João Silva";
    private String number = "001";
    private String partnerNumber = "12345";
    private String excessValue = "R$ 150,00";
    private String excessLimit = "100L";
    private String accountNumber = "ACC-2026-001";
    private String partnerCategory = "Residencial";
    private String dueDate = "15/03/2026";
    private String totalAmount = "R$ 250,00";
    private String address = "Rua Principal, 123 - Apt 42";
    private String date = "01/03/2026";
    private String payment = "";

    public InvoiceReportOutput(Invoice invoice) {
        this.partner = invoice.getPersonAddresses().getPerson().getName();
        this.number = invoice.getId().toString();
    }

    public String getPartner() {
        return partner;
    }

    public String getNumber() {
        return number;
    }

    public String getPartnerNumber() {
        return partnerNumber;
    }

    public String getExcessValue() {
        return excessValue;
    }

    public String getExcessLimit() {
        return excessLimit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPartnerCategory() {
        return partnerCategory;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getPayment() {
        return payment;
    }
}

