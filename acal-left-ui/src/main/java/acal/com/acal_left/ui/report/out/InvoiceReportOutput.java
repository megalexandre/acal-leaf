package acal.com.acal_left.ui.report.out;


import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class InvoiceReportOutput {

    private String id;
    private String partner;
    private String partnerNumber = "12345";
    private String excessValue = "R$ 150,00";
    private String excessLimit = "100L";
    private String accountNumber = "ACC-2026-001";
    private String partnerCategory = "Residencial";
    private String dueDate = "15/03/2026";
    private String totalAmount = "R$ 250,00";
    private String address = "Rua Principal, 123 - Apt 42";
    private String currentDate = "01/03/2026";
    private String payment = "";
    public List<InvoiceReportWaterParamOutput> waterAnalysis;

    private Boolean isPartnerExclusive;
    private Boolean isNormalPartner;

    public static InvoiceReportOutput fromDomain(Invoice invoice) {
        return InvoiceReportOutput.builder()
                .id(invoice.getId().toString())
                .partner(invoice.getPerson().getName())
                .partnerNumber(invoice.getPerson().getPartnerNumber())
                .partnerCategory(invoice.getCategory().getFullName())
                .totalAmount(BigDecimalUtil.toBRL(invoice.totalAmount()))
                .currentDate(LocalDateTimeUtil.formatDateTime(LocalDateTime.now()))
                .dueDate(invoice.getDueDate().toString())
                .waterAnalysis(invoice.getWaterAnalysis().getAnalysis().stream().map(InvoiceReportWaterParamOutput::new).toList())
                .excessValue(invoice.getAmountWater().subtract(invoice.getAmountPartner()).toString())
                .excessLimit("100L")
                .address("invoice.getPerson().getAddress()")
                .payment(invoice.isPaid() ? "Pago" : "Pendente")
                .isPartnerExclusive(false)
                .isNormalPartner(false)
                .build();
    }

}

