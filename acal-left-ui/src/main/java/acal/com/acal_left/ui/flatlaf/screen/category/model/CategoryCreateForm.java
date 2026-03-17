package acal.com.acal_left.ui.flatlaf.screen.category.model;

import acal.com.acal_left.shared.model.MemberGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateForm {

    @NotEmpty(message = "O nome não pode ser vazio")
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotNull(message = "O valor da água é obrigatório")
    private BigDecimal amountWater;

    @NotNull(message = "O valor do sócio é obrigatório")
    private BigDecimal amountPartner;

    @NotNull(message = "O grupo de membro é obrigatório")
    private MemberGroup memberGroup;

    @NotNull(message = "O hidrômetro é obrigatório")
    private Boolean isHydrometer;
}
