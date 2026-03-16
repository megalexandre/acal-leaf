package acal.com.acal_left.ui.flatlaf.screen.charge.model;

import acal.com.acal_left.core.model.Charge;
import acal.com.acal_left.core.model.Invoice;
import acal.com.acal_left.shared.BigDecimalUtil;
import lombok.Builder;
import lombok.val;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class ChargesDetails {

    private List<Charge> charges;

    public String getLabel() {
        if(charges == null || charges.isEmpty()){
            return "Nenhuma cobrança encontrada.";
        }

        return getCount() + getTotal();
    }

    private String getCount(){

        if (charges.size() == 1) {
            return "1 cobrança encontrada, com o valor de:";
        }

        return charges.size() + " cobranças encontradas, com o valor de:";
    }

    private String getTotal(){
        val invoices = charges.stream()
                .flatMap(charge -> charge.getInvoices().stream())
                .toList();

        var total = invoices.stream()
                .map(Invoice::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return BigDecimalUtil.toBRL(total);
    }

}
