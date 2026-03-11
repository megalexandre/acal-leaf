package acal.com.acal_left.core.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Hydrometer {

    private static final double PRICE_PER_UNIT = 0.004;
    public static final double FREE_TIER = 10000;

    private Integer id;
    private Double consumptionStart;
    private Double consumptionEnd;

    public BigDecimal getConsumption() {
        if (consumptionStart == null || consumptionEnd == null) {
            return BigDecimal.ZERO;
        }

        if((consumptionEnd - consumptionStart) < FREE_TIER) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal(consumptionEnd - consumptionStart);
    }

    public BigDecimal price() {
        BigDecimal consumption = getConsumption();
        BigDecimal freeTierBD = BigDecimal.valueOf(FREE_TIER);
        if (consumption.compareTo(freeTierBD) <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal excess = consumption.subtract(freeTierBD);
        return excess.multiply(BigDecimal.valueOf(PRICE_PER_UNIT));
    }

}
