package acal.com.acal_left.core.model

import lombok.Builder

@Builder
data class InvoiceQuery(
    val id: Int? = null,
    val categoryId: Int? = null,
    val addressId: Int? = null,
    val partnerId: Int? = null
)