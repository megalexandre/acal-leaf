package acal.com.acal_left.ui.flatlaf.screen.invoice.create.model;

import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class InvoicesLabel {

    private List<Invoice> invoices;

    public String getLabel() {
        return getCount() + getTotal();
    }

    private String getCount(){
        if (invoices == null || invoices.isEmpty()) {
            return "Nenhuma fatura encontrada.";
        }
        if (invoices.size() == 1) {
            return "1 fatura encontrada, com o valor de:";
        }

        return invoices.size() + " faturas encontradas, com o valor de:";
    }

    private String getTotal(){
        if (invoices == null || invoices.isEmpty()) {
            return "R$ 0,00";
        }

        var total = invoices.stream()
                .map(Invoice::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return BigDecimalUtil.toBRL(total);
    }

}
