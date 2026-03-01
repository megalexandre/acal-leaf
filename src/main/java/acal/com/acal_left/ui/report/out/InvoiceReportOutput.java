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

    private Boolean isPartnerExclusive;
    private Boolean isNormalPartner;

    public InvoiceReportOutput(Invoice invoice) {
        this.partner = invoice.getPersonAddresses().getPerson().getName();
        this.number = invoice.getId().toString();
        this.partnerNumber = invoice.getPersonAddresses().getNumber();
        this.isPartnerExclusive = invoice.isPartnerExclusive();
        this.isNormalPartner = invoice.isNormalPartner();
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

    public Boolean getPartnerExclusive() {
        return isPartnerExclusive;
    }

    public void setPartnerExclusive(Boolean partnerExclusive) {
        isPartnerExclusive = partnerExclusive;
    }

    public Boolean getNormalPartner() {
        return isNormalPartner;
    }

    public void setNormalPartner(Boolean normalPartner) {
        isNormalPartner = normalPartner;
    }
}

