package acal.com.acal_left.ui.flatlaf.screen.link.create;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.model.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkCreateForm {
    @NotBlank(message = "O número é obrigatório")
    private String number;

    @NotNull(message = "O sócio deve ser selecionado")
    private Person person;

    @NotNull(message = "A categoria deve ser selecionada")
    private Category category;

    @NotNull(message = "O endereço deve ser selecionado")
    private Address address;

}
