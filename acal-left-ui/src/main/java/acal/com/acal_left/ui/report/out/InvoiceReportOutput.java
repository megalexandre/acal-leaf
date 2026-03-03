package acal.com.acal_left.ui.report.out;


import acal.com.acal_left.core.model.Invoice;
import lombok.Getter;

@Getter
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
    }
}

