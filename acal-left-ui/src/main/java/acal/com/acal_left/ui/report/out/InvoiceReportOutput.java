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

    private String addressNumber;
    private String number;
    private String reference;

    private String partnerName;

    //Agua
    private String consumptionStart;
    private String consumptionEnd;
    private String consumptionValue;
    private String freeTier;
    private String partnerValue;

    private String paidUsageValue;

    private String partnerNumber;
    private String paidAt;

    private String excessValue;
    private String excessLimit;
    private String accountNumber;
    private String category;
    private String dueDate;
    private String total;
    private String categoryValue;
    private String address;
    private String currentDate;
    private String payment;

    public List<InvoiceReportWaterParamOutput> waterParams;

    private Boolean isPartnerExclusive;
    private Boolean isNormalPartner;

    public static InvoiceReportOutput fromDomain(Invoice invoice) {
        return InvoiceReportOutput.builder()
                .addressNumber(invoice.getNumber())
                .number(invoice.getId().toString())
                .reference(LocalDateUtil.formatPeriod(invoice.getPeriod()))
                .partnerName(invoice.getPerson().getName())
                .address(invoice.getAddress().getFullAddress())
                .category(invoice.getCategory().getFullName())
                .partnerNumber(invoice.getPerson().getPartnerNumber())
                .categoryValue(BigDecimalUtil.toBRL(invoice.getCategory().getAmountPartner()))
                .partnerValue(BigDecimalUtil.toBRL(invoice.getCategory().getAmountWater()))
                .consumptionStart("Registro anterior:" + "0")
                .consumptionEnd("Registro atual:" + "0")
                .consumptionValue("0")
                .paidAt(invoice.isPaid() ? "Pago em: " + LocalDateTimeUtil.formatDateTime(invoice.getPaidAt()) : "")
                .total(BigDecimalUtil.toBRL(invoice.totalAmount()))
                .currentDate(LocalDateTimeUtil.formatDateTime(LocalDateTime.now()))
                .dueDate(invoice.getDueDate().toString())
                .waterParams(invoice.getWaterAnalysis().getAnalysis().stream().map(InvoiceReportWaterParamOutput::new).toList())
                .excessValue(invoice.getAmountWater().subtract(invoice.getAmountPartner()).toString())
                .excessLimit("100L")
                .payment(invoice.isPaid() ? "Pago" : "Pendente")
                .isPartnerExclusive(false)
                .isNormalPartner(false)
            .build();
    }

}

