package acal.com.acal_left.ui.report.out;


import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.LocalDateTimeUtil;
import acal.com.acal_left.shared.LocalDateUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class InvoiceReportOutput {

    private String number;
    private String partnerName;
    private String partnerNumber;
    private String reference;
    private String paidAt;

    private String excessValue;
    private String excessLimit;
    private String accountNumber;
    private String category;
    private String dueDate;
    private String total;
    private String categoryValue;
    private String waterValue;
    private String address;
    private String addressNumber;
    private String currentDate;
    private String payment;

    public List<InvoiceReportWaterParamOutput> waterAnalysis;

    private Boolean isPartnerExclusive;
    private Boolean isNormalPartner;

    private String consumptionStart = "0";
    private String consumptionEnd = "0";
    private String freeTier = "10.000L";
    private String waterTotalValue = "0.00";
    private String paidUsageValue  = "0.00";

    public static InvoiceReportOutput fromDomain(Invoice invoice) {
        return InvoiceReportOutput.builder()
                .number(invoice.getId().toString())
                .partnerName(invoice.getPerson().getName())
                .partnerNumber(invoice.getPerson().getPartnerNumber())
                .reference(LocalDateUtil.formatPeriod(invoice.getPeriod()))
                .categoryValue(BigDecimalUtil.toBRL(invoice.getCategory().getAmountPartner()))
                .waterValue(BigDecimalUtil.toBRL(invoice.getCategory().getAmountWater()))

                .address(invoice.getAddress().getFullAddress())
                .addressNumber("10")
                .category(invoice.getCategory().getFullName())
                .total(BigDecimalUtil.toBRL(invoice.totalAmount()))
                .currentDate(LocalDateTimeUtil.formatDateTime(LocalDateTime.now()))
                .dueDate(invoice.getDueDate().toString())
                .waterAnalysis(invoice.getWaterAnalysis().getAnalysis().stream().map(InvoiceReportWaterParamOutput::new).toList())
                .excessValue(invoice.getAmountWater().subtract(invoice.getAmountPartner()).toString())
                .excessLimit("100L")
                .payment(invoice.isPaid() ? "Pago" : "Pendente")
                .isPartnerExclusive(false)
                .isNormalPartner(false)
                .build();
    }

}

