package acal.com.acal_left.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceQuery {
    private Integer id;
    private Integer categoryId;
    private Integer addressId;
    private Integer partnerId;
}

