package acal.com.acal_left.ui.report.out;


import acal.com.acal_left.core.model.Invoice;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InvoiceReportOutput {

    private String id;
    private String partner = "João Silva";
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

    public static InvoiceReportOutput fromDomain(Invoice invoice) {
        return InvoiceReportOutput.builder()
                .id(invoice.getId().toString())
                .partner(invoice.getPerson().getName())
                .partnerNumber("Ainda não fiz")
                .excessValue(invoice.getAmountWater().subtract(invoice.getAmountPartner()).toString())
                .excessLimit("100L")
                .accountNumber("ACC-" + invoice.getPeriod().getYear() + "-" + invoice.getId())
                .partnerCategory("Category")
                .dueDate(invoice.getDueDate().toString())
                .totalAmount(invoice.getAmountWater().toString())
                .address("invoice.getPerson().getAddress()")
                .date(invoice.getPeriod().toString())
                .payment(invoice.isPaid() ? "Pago" : "Pendente")
                .isPartnerExclusive(false)
                .isNormalPartner(false)
                .build();
    }

}

