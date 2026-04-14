package acal.com.acal_left.ui.flatlaf.screen.person.model;

import acal.com.acal_left.ui.flatlaf.validation.annotation.CpfOrCnpj;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateForm {

    @NotEmpty(message = "O nome não pode ser vazio")
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @CpfOrCnpj(message = "O documento deve ser um CPF ou CNPJ válido")
    private String document;

    // partnerNumber é opcional - sem anotações
    private String partnerNumber;
}


