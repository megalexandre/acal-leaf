package acal.com.acal_left.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private Integer id;

    //public boolean isPartnerExclusive() {
    //    return personAddresses.isPartnerExclusive();
    //}

    //public boolean isNormalPartner() {
    //        return !personAddresses.isPartnerExclusive();
    //    }
}
