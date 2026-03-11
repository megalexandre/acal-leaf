package acal.com.acal_left.ui.report.out;


import acal.com.acal_left.core.model.Hydrometer;
import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.shared.DoubleUtil;
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
    private String amountWater;

    private String paidUsageValue;

    private String partnerNumber;
    private String paidAt;

    private String excessValue;
    private String excessLimit;
    private String accountNumber;
    private String category;
    private String dueDate;
    private String total;
    private String amountPartner;
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

                .amountPartner(BigDecimalUtil.toBRL(invoice.getAmountPartner()))
                .amountWater(BigDecimalUtil.toBRL(invoice.getAmountWater()))

                .consumptionStart(getConsumptionStartLabel(invoice))
                .consumptionEnd(getConsumptionEndLabel(invoice))
                .freeTier(getFreeTierLabel())
                .consumptionValue(getConsumptionValueLabel(invoice))
                .paidUsageValue(paidUsageValue(invoice))


                .paidAt(invoice.isPaid() ? "Pago em: " + LocalDateTimeUtil.formatDateTime(invoice.getPaidAt()) : "")
                .total(BigDecimalUtil.toBRL(invoice.totalAmount()))
                .currentDate(LocalDateTimeUtil.formatDateTime(LocalDateTime.now()))
                .dueDate(invoice.getDueDate().toString())
                .waterParams(invoice.getWaterAnalysis().getAnalysis().stream().map(InvoiceReportWaterParamOutput::new).toList())
                .excessValue(invoice.getAmountWater().subtract(invoice.getAmountPartner()).toString())
                .payment(invoice.isPaid() ? "Pago" : "Pendente")
                .isPartnerExclusive(false)
                .isNormalPartner(false)
            .build();
    }

    private static String paidUsageValue(Invoice invoice) {
        return BigDecimalUtil.toBRL(invoice.hydrometer.price());
    }

    private static String getConsumptionValueLabel(Invoice invoice) {
        Double consumption = invoice.hydrometer.getConsumption().doubleValue();
        return "Consumo Considerado: " +  DoubleUtil.format( consumption)+ " L";
    }

    private static String getFreeTierLabel(){
        return "Isenção: " + DoubleUtil.format(Hydrometer.FREE_TIER) + " L";
    }

    private static String getConsumptionStartLabel(Invoice invoice){
        return "Registro anterior: " + DoubleUtil.format(invoice.hydrometer.getConsumptionStart());
    }

    private static String getConsumptionEndLabel(Invoice invoice){
        return "Registro atual: " + DoubleUtil.format(invoice.hydrometer.getConsumptionEnd());
    }


}

